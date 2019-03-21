package com.seamwhole.servicetradecore.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.cache.J2CacheUtils;
import com.platform.dao.ApiCouponMapper;
import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.qiniu.util.StringUtils;
import com.seamwhole.servicetradecore.constant.RedisKeyConstant;
import com.seamwhole.servicetradecore.controller.model.CartModel;
import com.seamwhole.servicetradecore.domain.BuyGoods;
import com.seamwhole.servicetradecore.mapper.model.ShopCartModel;
import com.seamwhole.servicetradecore.model.*;
import com.seamwhole.servicetradecore.redis.RedisService;
import com.seamwhole.servicetradecore.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "购物车")
@RestController
@RequestMapping("/api/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ApiGoodsService goodsService;
    @Autowired
    private ApiProductService productService;
    @Autowired
    private ApiGoodsSpecificationService goodsSpecificationService;
    @Autowired
    private ApiAddressService addressService;
    @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private ApiCouponMapper apiCouponMapper;

    @Autowired
    private RedisService redisService;


    /**
     * 获取购物车中的数据
     */
    @ApiOperation(value = "获取购物车中的数据")
    @PostMapping("getCart")
    public Object getCart(@RequestBody CartModel cartModel) {
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据

        List<ShopCartModel> cartList = cartService.queryList(cartModel.getUserId(), null, null,null , "");
        //获取购物车统计信息
        Integer goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0.00);
        Integer checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (ShopCart cartItem : cartList) {
            goodsCount += cartItem.getNumber();
            goodsAmount = goodsAmount.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            if (null != cartItem.getChecked() && true == cartItem.getChecked()) {
                checkedGoodsCount += cartItem.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            }
        }
        // 获取优惠信息提示
        Map couponParam = new HashMap();
        couponParam.put("enabled", true);
        Integer[] send_types = new Integer[]{0, 7};
        couponParam.put("send_types", send_types);
        List<CouponInfoVo> couponInfoList = new ArrayList();
        List<Coupon> couponVos = apiCouponService.queryList(couponParam);
        if (null != couponVos && couponVos.size() > 0) {
            CouponInfoVo fullCutVo = new CouponInfoVo();
            BigDecimal fullCutDec = new BigDecimal(0);
            BigDecimal minAmount = new BigDecimal(100000);
            for (Coupon couponVo : couponVos) {
                BigDecimal difDec = couponVo.getMin_goods_amount().subtract(checkedGoodsAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (couponVo.getSendType() == 0 && difDec.doubleValue() > 0.0
                        && minAmount.compareTo(couponVo.getMinGoodsAmount()) > 0) {
                    fullCutDec = couponVo.getTypeMoney();
                    minAmount = couponVo.getMinGoodsAmount();
                    fullCutVo.setType(1);
                    fullCutVo.setMsg(couponVo.getName() + "，还差" + difDec + "元");
                } else if (couponVo.getSendType() == 0 && difDec.doubleValue() < 0.0 && fullCutDec.compareTo(couponVo.getTypeMoney()) < 0) {
                    fullCutDec = couponVo.getTypeMoney();
                    fullCutVo.setType(0);
                    fullCutVo.setMsg("可使用满减券" + couponVo.getName());
                }
                if (couponVo.getSendType() == 7 && difDec.doubleValue() > 0.0) {
                    CouponInfoVo cpVo = new CouponInfoVo();
                    cpVo.setMsg("满￥" + couponVo.getMin_amount() + "元免配送费，还差" + difDec + "元");
                    cpVo.setType(1);
                    couponInfoList.add(cpVo);
                } else if (couponVo.getSendType() == 7) {
                    CouponInfoVo cpVo = new CouponInfoVo();
                    cpVo.setMsg("满￥" + couponVo.getMinAmount() + "元免配送费");
                    couponInfoList.add(cpVo);
                }
            }
            if (!StringUtils.isNullOrEmpty(fullCutVo.getMsg())) {
                couponInfoList.add(fullCutVo);
            }
        }
        resultObj.put("couponInfoList", couponInfoList);
        resultObj.put("cartList", cartList);
        //
        Map<String, Object> cartTotal = new HashMap();
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);
        //
        resultObj.put("cartTotal", cartTotal);
        return resultObj;
    }

    /**
     * 获取购物车信息，所有对购物车的增删改操作，都要重新返回购物车的信息
     */
    @ApiOperation(value = "获取购物车信息")
    @PostMapping("index")
    public Object index(@LoginUser UserVo loginUser) {
        return toResponsSuccess(getCart(loginUser));
    }

    private String[] getSpecificationIdsArray(String ids) {
        String[] idsArray = null;
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] tempArray = ids.split("_");
            if (null != tempArray && tempArray.length > 0) {
                idsArray = tempArray;
            }
        }
        return idsArray;
    }

    /**
     * 添加商品到购物车
     */
    @ApiOperation(value = "添加商品到购物车")
    @PostMapping("add")
    public Object add(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer productId = jsonParam.getInteger("productId");
        Short number = jsonParam.getShort("number");
        //判断商品是否可以购买
        Goods goodsInfo = goodsService.queryObject(goodsId);
        if (null == goodsInfo || goodsInfo.getIsDelete() == true || goodsInfo.getIsOnSale() != true) {
            return this.toResponsObject(400, "商品已下架", "");
        }
        //取得规格的信息,判断规格库存
        Product productInfo = productService.queryObject(productId);
        if (null == productInfo || productInfo.getGoodsNumber() < number) {
            return this.toResponsObject(400, "库存不足", "");
        }

        //判断购物车中是否存在此规格商品
        Map cartParam = new HashMap();
        cartParam.put("goods_id", goodsId);
        cartParam.put("product_id", productId);
        cartParam.put("user_id", loginUser.getUserId());
        List<ShopCart> cartInfoList = cartService.queryList(cartParam, , , ,"" );
        ShopCart cartInfo = null != cartInfoList && cartInfoList.size() > 0 ? cartInfoList.get(0) : null;
        if (null == cartInfo) {
            //添加操作
            //添加规格名和值
            String[] goodsSepcifitionValue = null;
            if (null != productInfo.getGoodsSpecificationIds() && productInfo.getGoodsSpecificationIds().length() > 0) {
                Map specificationParam = new HashMap();
                String[] idsArray = getSpecificationIdsArray(productInfo.getGoodsSpecificationIds());
                specificationParam.put("ids", idsArray);
                specificationParam.put("goods_id", goodsId);
                List<GoodsSpecification> specificationEntities = goodsSpecificationService.queryList(specificationParam);
                goodsSepcifitionValue = new String[specificationEntities.size()];
                for (int i = 0; i < specificationEntities.size(); i++) {
                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
                }
            }
            cartInfo = new ShopCart();

            cartInfo.setGoodsId(goodsId);
            cartInfo.setProductId(productId);
            cartInfo.setGoodsSn(productInfo.getGoodsSn());
            cartInfo.setGoodsName(goodsInfo.getName());
            cartInfo.setListPicUrl(goodsInfo.getListPicUrl());
            cartInfo.setNumber(number);
            cartInfo.setSessionId("1");
            cartInfo.setUserId(loginUser.getUserId());
            cartInfo.setRetailPrice(productInfo.getRetailPrice());
            cartInfo.setMarketPrice(productInfo.getMarketPrice());
            if (null != goodsSepcifitionValue) {
                cartInfo.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValue, ";"));
            }
            cartInfo.setGoodsSpecifitionIds(productInfo.getGoodsSpecificationIds());
            cartInfo.setChecked(true);
            cartService.save(cartInfo);
        } else {
            //如果已经存在购物车中，则数量增加
            if (productInfo.getGoodsNumber() < (number + cartInfo.getNumber())) {
                return this.toResponsObject(400, "库存不足", "");
            }
            cartInfo.setNumber(cartInfo.getNumber() + number);
            cartService.update(cartInfo);
        }
        return toResponsSuccess(getCart(loginUser));
    }

    /**
     * 减少商品到购物车
     */
    @ApiOperation(value = "减少商品到购物车")
    @PostMapping("minus")
    public Object minus(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer productId = jsonParam.getInteger("productId");
        Integer number = jsonParam.getInteger("number");
        //判断购物车中是否存在此规格商品
        Map cartParam = new HashMap();
        cartParam.put("goods_id", goodsId);
        cartParam.put("product_id", productId);
        cartParam.put("user_id", loginUser.getUserId());
        List<ShopCart> cartInfoList = cartService.queryList(cartParam, , , , );
        ShopCart cartInfo = null != cartInfoList && cartInfoList.size() > 0 ? cartInfoList.get(0) : null;
        int cart_num = 0;
        if (null != cartInfo) {
            if (cartInfo.getNumber() > number) {
                cartInfo.setNumber(cartInfo.getNumber() - number);
                cartService.update(cartInfo);
                cart_num = cartInfo.getNumber();
            } else if (cartInfo.getNumber() == 1) {
                cartService.delete(cartInfo.getId());
                cart_num = 0;
            }
        }
        return toResponsSuccess(cart_num);
    }

    /**
     * 更新指定的购物车信息
     */
    @ApiOperation(value = "更新指定的购物车信息")
    @PostMapping("update")
    public Object update(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer productId = jsonParam.getInteger("productId");
        Short number = jsonParam.getShort("number");
        Integer id = jsonParam.getInteger("id");
        //取得规格的信息,判断规格库存
        Product productInfo = productService.queryObject(productId);
        if (null == productInfo || productInfo.getGoodsNumber() < number) {
            return this.toResponsObject(400, "库存不足", "");
        }
        //判断是否已经存在product_id购物车商品
        ShopCart cartInfo = cartService.queryObject(id);
        //只是更新number
        if (cartInfo.getProductId().equals(productId)) {
            cartInfo.setNumber(number);
            cartService.update(cartInfo);
            return toResponsSuccess(getCart(loginUser));
        }

        Map cartParam = new HashMap();
        cartParam.put("goodsId", goodsId);
        cartParam.put("productId", productId);
        List<ShopCart> cartInfoList = cartService.queryList(cartParam, , , , );
        ShopCart newcartInfo = null != cartInfoList && cartInfoList.size() > 0 ? cartInfoList.get(0) : null;
        if (null == newcartInfo) {
            //添加操作
            //添加规格名和值
            String[] goodsSepcifitionValue = null;
            if (null != productInfo.getGoodsSpecificationIds()) {
                Map specificationParam = new HashMap();
                specificationParam.put("ids", productInfo.getGoodsSpecificationIds());
                specificationParam.put("goodsId", goodsId);
                List<GoodsSpecification> specificationEntities = goodsSpecificationService.queryList(specificationParam);
                goodsSepcifitionValue = new String[specificationEntities.size()];
                for (int i = 0; i < specificationEntities.size(); i++) {
                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
                }
            }
            cartInfo.setProductId(productId);
            cartInfo.setGoodsSn(productInfo.getGoodsSn());
            cartInfo.setNumber(number);
            cartInfo.setRetailPrice(productInfo.getRetailPrice());
            cartInfo.setMarketPrice(productInfo.getRetailPrice());
            if (null != goodsSepcifitionValue) {
                cartInfo.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValue, ";"));
            }
            cartInfo.setGoodsSpecifitionIds(productInfo.getGoodsSpecificationIds());
            cartService.update(cartInfo);
        } else {
            //合并购物车已有的product信息，删除已有的数据
            Integer newNumber = number + newcartInfo.getNumber();
            if (null == productInfo || productInfo.getGoodsNumber() < newNumber) {
                return this.toResponsObject(400, "库存不足", "");
            }
            cartService.delete(newcartInfo.getId());
            //添加规格名和值
            String[] goodsSepcifitionValue = null;
            if (null != productInfo.getGoodsSpecificationIds()) {
                Map specificationParam = new HashMap();
                specificationParam.put("ids", productInfo.getGoodsSpecificationIds());
                specificationParam.put("goodsId", goodsId);
                List<GoodsSpecification> specificationEntities = goodsSpecificationService.queryList(specificationParam);
                goodsSepcifitionValue = new String[specificationEntities.size()];
                for (int i = 0; i < specificationEntities.size(); i++) {
                    goodsSepcifitionValue[i] = specificationEntities.get(i).getValue();
                }
            }
            cartInfo.setProductId(productId);
            cartInfo.setGoodsSn(productInfo.getGoodsSn());
            cartInfo.setNumber(number);
            cartInfo.setRetailPrice(productInfo.getRetailPrice());
            cartInfo.setMarketPrice(productInfo.getRetailPrice());
            if (null != goodsSepcifitionValue) {
                cartInfo.setGoodsSpecifitionNameValue(StringUtils.join(goodsSepcifitionValue, ";"));
            }
            cartInfo.setGoodsSpecifitionIds(productInfo.getGoodsSpecificationIds());
            cartService.update(cartInfo);
        }
        return toResponsSuccess(getCart(loginUser));
    }

    /**
     * 是否选择商品，如果已经选择，则取消选择，批量操作
     */
    @ApiOperation(value = "是否选择商品")
    @PostMapping("checked")
    public Object checked(@LoginUser UserVo loginUser) {
        JSONObject jsonParam = getJsonRequest();
        String productIds = jsonParam.getString("productIds");
        Integer isChecked = jsonParam.getInteger("isChecked");
        if (StringUtils.isNullOrEmpty(productIds)) {
            return this.toResponsFail("删除出错");
        }
        String[] productIdArray = productIds.split(",");
        cartService.updateCheck(productIdArray, isChecked, loginUser.getUserId());
        return toResponsSuccess(getCart(loginUser));
    }

    //删除选中的购物车商品，批量删除
    @ApiOperation(value = "删除商品")
    @PostMapping("delete")
    public Object delete(@LoginUser UserVo loginUser) {
        Long userId = loginUser.getUserId();

        JSONObject jsonObject = getJsonRequest();
        String productIds = jsonObject.getString("productIds");

        if (StringUtils.isNullOrEmpty(productIds)) {
            return toResponsFail("删除出错");
        }
        String[] productIdsArray = productIds.split(",");
        cartService.deleteByUserAndProductIds(userId, productIdsArray);

        return toResponsSuccess(getCart(loginUser));
    }

    //  获取购物车商品的总件件数
    @ApiOperation(value = "获取购物车商品的总件件数")
    @PostMapping("goodscount")
    public Object goodscount(@LoginUser UserVo loginUser) {
        if (null == loginUser || null == loginUser.getUserId()) {
            return toResponsFail("未登录");
        }
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据
        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        List<ShopCart> cartList = cartService.queryList(param, , , , );
        //获取购物车统计信息
        Integer goodsCount = 0;
        for (ShopCart cartItem : cartList) {
            goodsCount += cartItem.getNumber();
        }
        resultObj.put("cartList", cartList);
        //
        Map<String, Object> cartTotal = new HashMap();
        cartTotal.put("goodsCount", goodsCount);
        //
        resultObj.put("cartTotal", cartTotal);
        return toResponsSuccess(resultObj);
    }

    /**
     * 订单提交前的检验和填写相关订单信息
     */
    @ApiOperation(value = "订单提交前的检验和填写相关订单信息")
    @PostMapping("checkout")
    public Object checkout(@ResponseBody CartModel cartModel, @LoginUser UserVo loginUser, Integer couponId, @RequestParam(defaultValue = "cart") String type) {
        Map<String, Object> resultObj = new HashMap();
        //根据收货地址计算运费

        BigDecimal freightPrice = new BigDecimal(0.00);
        //默认收货地址
        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        List addressEntities = addressService.queryList(param);

        if (null == addressEntities || addressEntities.size() == 0) {
            resultObj.put("checkedAddress", new ShopAddress());
        } else {
            resultObj.put("checkedAddress", addressEntities.get(0));
        }
        // * 获取要购买的商品和总价
        ArrayList checkedGoodsList = new ArrayList();
        BigDecimal goodsTotalPrice;
        if (type.equals("cart")) {
            Map<String, Object> cartData = (Map<String, Object>) this.getCart(loginUser);

            for (ShopCart cartEntity : (List<ShopCart>) cartData.get("cartList")) {
                if (cartEntity.getChecked() == 1) {
                    checkedGoodsList.add(cartEntity);
                }
            }
            goodsTotalPrice = (BigDecimal) ((HashMap) cartData.get("cartTotal")).get("checkedGoodsAmount");
        } else { // 是直接购买的
            BuyGoods goodsVO = (BuyGoods) redisService.get(cartModel.getUserId()+ RedisKeyConstant.BUY_GOODS_CACHE);;
            Product productInfo = productService.queryObject(goodsVO.getProductId());
            //计算订单的费用
            //商品总价
            goodsTotalPrice = productInfo.getRetailPrice().multiply(new BigDecimal(goodsVO.getNumber()));

            ShopCart cartVo = new ShopCart();
            cartVo.setGoodsName(productInfo.getGoods_name());
            cartVo.setNumber(goodsVO.getNumber());
            cartVo.setRetailPrice(productInfo.getRetailPrice());
            cartVo.setListPicUrl(productInfo.getList_pic_url());
            checkedGoodsList.add(cartVo);
        }


        //获取可用的优惠券信息
        BigDecimal couponPrice = new BigDecimal(0.00);
        if (couponId != null && couponId != 0) {
            Coupon couponVo = apiCouponMapper.getUserCoupon(couponId);
            if (couponVo != null) {
                couponPrice = couponVo.getTypeMoney();
            }
        }

        //订单的总价
        BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice);

        //
        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额

        resultObj.put("freightPrice", freightPrice);

        resultObj.put("couponPrice", couponPrice);
        resultObj.put("checkedGoodsList", checkedGoodsList);
        resultObj.put("goodsTotalPrice", goodsTotalPrice);
        resultObj.put("orderTotalPrice", orderTotalPrice);
        resultObj.put("actualPrice", actualPrice);
        return toResponsSuccess(resultObj);
    }

    /**
     * 选择优惠券列表
     */
    @ApiOperation(value = "选择优惠券列表")
    @PostMapping("checkedCouponList")
    public Object checkedCouponList(@LoginUser UserVo loginUser) {
        //
        Map param = new HashMap();
        param.put("user_id", loginUser.getUserId());
        List<Coupon> couponVos = apiCouponService.queryUserCouponList(param);
        if (null != couponVos && couponVos.size() > 0) {
            // 获取要购买的商品
            Map<String, Object> cartData = (Map<String, Object>) this.getCart(loginUser);
            List<ShopCart> checkedGoodsList = new ArrayList();
            List<Integer> checkedGoodsIds = new ArrayList();
            for (ShopCart cartEntity : (List<ShopCart>) cartData.get("cartList")) {
                if (cartEntity.getChecked() == true) {
                    checkedGoodsList.add(cartEntity);
                    checkedGoodsIds.add(cartEntity.getId());
                }
            }
            // 计算订单的费用
            BigDecimal goodsTotalPrice = (BigDecimal) ((HashMap) cartData.get("cartTotal")).get("checkedGoodsAmount");  //商品总价
            // 如果没有用户优惠券直接返回新用户优惠券
            for (Coupon couponVo : couponVos) {
                if (couponVo.getMinAmount().compareTo(goodsTotalPrice) <= 0) {
                    couponVo.setEnabled(1);
                }
            }
        }
        return toResponsSuccess(couponVos);
    }
}
