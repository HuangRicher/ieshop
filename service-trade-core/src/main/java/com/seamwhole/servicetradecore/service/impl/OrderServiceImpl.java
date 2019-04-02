package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.constant.RedisKeyConstant;
import com.seamwhole.servicetradecore.domain.BuyGoods;
import com.seamwhole.servicetradecore.mapper.*;
import com.seamwhole.servicetradecore.mapper.ext.OrderExtMapper;
import com.seamwhole.servicetradecore.mapper.model.CouponDO;
import com.seamwhole.servicetradecore.mapper.model.OrderDO;
import com.seamwhole.servicetradecore.mapper.model.ShopCartDO;
import com.seamwhole.servicetradecore.model.*;
import com.seamwhole.servicetradecore.redis.RedisService;
import com.seamwhole.servicetradecore.service.*;
import com.seamwhole.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderExtMapper orderExtMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ShippingService shippingService;

    public OrderDO queryObject(Integer id) {
        return orderExtMapper.queryObject(id);
    }


    @Override
    public PageInfo<Order> findByPage(Integer userId, Integer pageNum, Integer pageSize, String order) {
        OrderExample example=new OrderExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()->orderMapper.selectByExample(example));
    }

    public void save(Order order) {
        orderMapper.insertSelective(order);
    }


    public int update(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }


    public void delete(Integer id) {
        orderMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        OrderExample example=new OrderExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        orderMapper.deleteByExample(example);
    }


    @Transactional
    public Map<String, Object> submit(Integer couponId,String type,String postscript,Integer addressId,Integer userId) {
        Map<String, Object> resultObj = new HashMap<String, Object>();

//        AddressVo addressVo = jsonParam.getObject("checkedAddress",AddressVo.class);
        ShopAddress addressVo = addressService.getById(addressId);


        Integer freightPrice = 0;

        // * 获取要购买的商品
        List<ShopCartDO> checkedGoodsList = new ArrayList<>();
        BigDecimal goodsTotalPrice;
        if (type.equals("cart")) {
            checkedGoodsList = cartService.queryList(userId, "1", null,null,1,"");
            if (null == checkedGoodsList) {
                resultObj.put("errno", 400);
                resultObj.put("errmsg", "请选择商品");
                return resultObj;
            }
            //统计商品总价
            goodsTotalPrice = new BigDecimal(0.00);
            for (ShopCart cartItem : checkedGoodsList) {
                goodsTotalPrice = goodsTotalPrice.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            }
        } else {
            BuyGoods goodsVo = (BuyGoods) redisService.get(userId+ RedisKeyConstant.BUY_GOODS_CACHE);
            Product productInfo = productService.queryObject(goodsVo.getProductId());
            //计算订单的费用
            //商品总价
            goodsTotalPrice = productInfo.getRetailPrice().multiply(new BigDecimal(goodsVo.getNumber()));

            ShopCart cartVo = new ShopCart();
            BeanUtils.copyProperties(productInfo, cartVo);
            cartVo.setNumber(goodsVo.getNumber());
            cartVo.setProductId(goodsVo.getProductId());
            ShopCartDO shopCartDO=new ShopCartDO();
            BeanUtils.copyProperties(cartVo,shopCartDO);
            checkedGoodsList.add(shopCartDO);
        }


        //获取订单使用的优惠券
        BigDecimal couponPrice = new BigDecimal(0.00);
        CouponDO couponVo = null;
        if (couponId != null && couponId != 0) {
            couponVo = couponService.getUserCoupon(couponId);
            if (couponVo != null && couponVo.getCoupon_status() == 1) {
                couponPrice = couponVo.getType_money();
            }
        }

        //订单价格计算
        BigDecimal orderTotalPrice = goodsTotalPrice.add(new BigDecimal(freightPrice)); //订单的总价

        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额

        Long currentTime = System.currentTimeMillis() / 1000;

        //
        Order orderInfo = new Order();
        orderInfo.setOrderSn(CommonUtil.generateOrderNumber());
        orderInfo.setUserId(userId);
        //收货地址和运费
        orderInfo.setConsignee(addressVo.getUserName());
        orderInfo.setMobile(addressVo.getTelNumber());
        orderInfo.setCountry(addressVo.getNationalCode());
        orderInfo.setProvince(addressVo.getProvinceName());
        orderInfo.setCity(addressVo.getCityName());
        orderInfo.setDistrict(addressVo.getCountyName());
        orderInfo.setAddress(addressVo.getDetailInfo());
        //
        orderInfo.setFreightPrice(freightPrice);
        //留言
        orderInfo.setPostscript(postscript);
        //使用的优惠券
        orderInfo.setCouponId(couponId);
        orderInfo.setCouponPrice(couponPrice);
        orderInfo.setAddTime(new Date());
        orderInfo.setGoodsPrice(goodsTotalPrice);
        orderInfo.setOrderPrice(orderTotalPrice);
        orderInfo.setActualPrice(actualPrice);
        // 待付款
        orderInfo.setOrderStatus(0);
        orderInfo.setShippingStatus(0);
        orderInfo.setPayStatus(0);
        orderInfo.setShippingId(0);
        orderInfo.setShippingFee(new BigDecimal(0));
        orderInfo.setIntegral(0);
        orderInfo.setIntegralMoney(new BigDecimal(0));
        if (type.equals("cart")) {
            orderInfo.setOrderType("1");
        } else {
            orderInfo.setOrderType("4");
        }

        //开启事务，插入订单信息和订单商品
        orderMapper.insertSelective(orderInfo);
        if (null == orderInfo.getId()) {
            resultObj.put("errno", 1);
            resultObj.put("errmsg", "订单提交失败");
            return resultObj;
        }
        //统计商品总价
        List<OrderGoods> orderGoodsData = new ArrayList<OrderGoods>();
        for (ShopCart goodsItem : checkedGoodsList) {
            OrderGoods orderGoodsVo = new OrderGoods();
            orderGoodsVo.setOrderId(orderInfo.getId());
            orderGoodsVo.setGoodsId(goodsItem.getGoodsId());
            orderGoodsVo.setGoodsSn(goodsItem.getGoodsSn());
            orderGoodsVo.setProductId(goodsItem.getProductId());
            orderGoodsVo.setGoodsName(goodsItem.getGoodsName());
            orderGoodsVo.setListPicUrl(goodsItem.getListPicUrl());
            orderGoodsVo.setMarketPrice(goodsItem.getMarketPrice());
            orderGoodsVo.setRetailPrice(goodsItem.getRetailPrice());
            orderGoodsVo.setNumber(goodsItem.getNumber());
            orderGoodsVo.setGoodsSpecifitionNameValue(goodsItem.getGoodsSpecifitionNameValue());
            orderGoodsVo.setGoodsSpecifitionIds(goodsItem.getGoodsSpecifitionIds());
            orderGoodsData.add(orderGoodsVo);
            orderGoodsMapper.insertSelective(orderGoodsVo);
        }

        //清空已购买的商品
        cartService.deleteByCart(userId, "1", 1);
        resultObj.put("errno", 0);
        resultObj.put("errmsg", "订单提交成功");
        //
        Map<String, Order> orderInfoMap = new HashMap<String, Order>();
        orderInfoMap.put("orderInfo", orderInfo);
        //
        resultObj.put("data", orderInfoMap);
        // 优惠券标记已用
        if (couponVo != null && couponVo.getCoupon_status() == 1) {
            couponVo.setCoupon_status(2);
            couponService.updateUserCoupon(couponVo);
        }

        return resultObj;
    }

    @Override
    public PageInfo<OrderDO> queryShopOrderByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<OrderDO> page=PageHelper.startPage(pageNum,pageSize);
        orderExtMapper.queryShopOrderList(params);
        return page.toPageInfo();
    }

    @Override
    public OrderDO getShopOrderById(Integer id) {
        return orderExtMapper.queryShopOrderObject(id);
    }

    @Override
    public List<OrderDO> queryShopOrderList(Map<String, Object> params) {
        return orderExtMapper.queryShopOrderList(params);
    }

    @Override
    public int getTotalCount(Map<String, Object> params) {
        OrderExample example=new OrderExample();
        return orderMapper.countByExample(example);
    }

    @Override
    public void confirm(Integer id) {
        OrderDO orderEntity = getShopOrderById(id);
        Integer shippingStatus = orderEntity.getShippingStatus();//发货状态
        Integer payStatus = orderEntity.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new CheckException("此订单未付款，不能确认收货！");
        }
        if (4 == shippingStatus) {
            throw new CheckException("此订单处于退货状态，不能确认收货！");
        }
        if (0 == shippingStatus) {
            throw new CheckException("此订单未发货，不能确认收货！");
        }
        orderEntity.setShippingStatus(2);
        orderEntity.setOrderStatus(301);
        orderMapper.updateByPrimaryKeySelective(orderEntity);
    }

    @Override
    public void sendGoods(Order order) {
        Integer payStatus = order.getPayStatus();//付款状态
        if (2 != payStatus) {
            throw new CheckException("此订单未付款！");
        }

        ShopShipping shippingEntity = shippingService.getById(order.getShippingId());
        if (null != shippingEntity) {
            order.setShippingName(shippingEntity.getName());
        }
        order.setOrderStatus(300);//订单已发货
        order.setShippingStatus(1);//已发货
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
