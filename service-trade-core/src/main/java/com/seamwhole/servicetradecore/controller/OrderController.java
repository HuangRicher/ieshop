package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.OrderModel;
import com.seamwhole.servicetradecore.domain.OrderInfo;
import com.seamwhole.servicetradecore.mapper.model.OrderDO;
import com.seamwhole.servicetradecore.model.Order;
import com.seamwhole.servicetradecore.model.OrderGoods;
import com.seamwhole.servicetradecore.service.ApiKdniaoService;
import com.seamwhole.servicetradecore.service.OrderGoodsService;
import com.seamwhole.servicetradecore.service.OrderService;
import com.seamwhole.util.PageObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: IndexController <br>
 */
@Api(tags = "订单相关")
@RestController
@RequestMapping("/api/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private ApiKdniaoService apiKdniaoService;

    /**
     */
    @ApiOperation(value = "订单首页")
    @PostMapping("index")
    public Object index() {
        //
        return toResponsSuccess("");
    }

    /**
     * 获取订单列表
     */
    @ApiOperation(value = "获取订单列表")
    @PostMapping("list")
    public Object list(@RequestBody OrderModel orderModel) {
        //
        Map params = new HashMap();
        params.put("user_id", orderModel.getUserId());
        params.put("page", orderModel.getPageNum());
        params.put("limit", orderModel.getPageSize());
        params.put("sidx", "id");
        params.put("order", "asc");
        //查询列表数据

        PageInfo<Order> pageInfo=orderService.findByPage(orderModel.getUserId(),orderModel.getPageNum(),orderModel.getPageSize()," id asc ");
        List<Order> orderEntityList = pageInfo.getList();
        List<OrderInfo> list=new ArrayList<>();
        //
        for (Order item : orderEntityList) {
            OrderInfo info=new OrderInfo();
            BeanUtils.copyProperties(item,info);
            Map orderGoodsParam = new HashMap();
            orderGoodsParam.put("order_id", item.getId());
            //订单的商品
            List<OrderGoods> goodsList = orderGoodsService.queryList(orderGoodsParam);
            Integer goodsCount = 0;
            for (OrderGoods orderGoodsEntity : goodsList) {
                goodsCount += orderGoodsEntity.getNumber();
                info.setGoodsCount(goodsCount);
            }
        }
        PageObject<OrderInfo> page=new PageObject<OrderInfo>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),list);
        return toResponsSuccess(page);
    }

    /**
     * 获取订单详情
     */
    @ApiOperation(value = "获取订单详情")
    @PostMapping("detail")
    public Object detail(@RequestBody OrderModel orderModel) {
        Map resultObj = new HashMap();
        //
        OrderDO orderInfo = orderService.queryObject(orderModel.getOrderId());
        if (null == orderInfo) {
            return toResponsObject(400, "订单不存在", "");
        }
        Map orderGoodsParam = new HashMap();
        orderGoodsParam.put("orderId", orderModel.getOrderId());
        //订单的商品
        List<OrderGoods> orderGoods = orderGoodsService.queryList(orderGoodsParam);
        //订单最后支付时间
        if (orderInfo.getOrder_status() == 0) {
            // if (moment().subtract(60, 'minutes') < moment(orderInfo.add_time)) {
//            orderInfo.final_pay_time = moment("001234", "Hmmss").format("mm:ss")
            // } else {
            //     //超过时间不支付，更新订单状态为取消
            // }
        }

        //订单可操作的选择,删除，支付，收货，评论，退换货
        Map handleOption = orderInfo.getHandleOption();
        //
        resultObj.put("orderInfo", orderInfo);
        resultObj.put("orderGoods", orderGoods);
        resultObj.put("handleOption", handleOption);
        if (!StringUtils.isEmpty(orderInfo.getShipping_code()) && !StringUtils.isEmpty(orderInfo.getShipping_no())) {
            // 快递
            List Traces = apiKdniaoService.getOrderTracesByJson(orderInfo.getShipping_code(), orderInfo.getShipping_no());
            resultObj.put("shippingList", Traces);
        }
        return toResponsSuccess(resultObj);
    }

    @ApiOperation(value = "修改订单")
    @PostMapping("updateSuccess")
    public Object updateSuccess(@RequestBody OrderModel orderModel) {
        OrderDO orderInfo = orderService.queryObject(orderModel.getOrderId());
        if (orderInfo == null) {
            return toResponsFail("订单不存在");
        } else if (orderInfo.getOrder_status() != 0) {
            return toResponsFail("订单状态不正确orderStatus" + orderInfo.getOrder_status() + "payStatus" + orderInfo.getPay_status());
        }

        Order order=new Order();
        BeanUtils.copyProperties(orderInfo,order);
        order.setId(orderModel.getOrderId());
        order.setPayStatus(2);
        order.setOrderStatus(201);
        order.setShippingStatus(0);
        order.setPayTime(new Date());
        int num = orderService.update(order);
        if (num > 0) {
            return toResponsMsgSuccess("修改订单成功");
        } else {
            return toResponsFail("修改订单失败");
        }
    }

    /**
     * 获取订单列表
     */
    @ApiOperation(value = "订单提交")
    @PostMapping("submit")
    public Object submit(@RequestBody OrderModel orderModel) {
        Map resultObj = null;
        try {
            resultObj = orderService.submit(orderModel.getCouponId(),orderModel.getType(),orderModel.getPostscript(),orderModel.getAddressId(),orderModel.getUserId());
            if (null != resultObj) {
                return toResponsObject(MapUtils.getInteger(resultObj, "errno"), MapUtils.getString(resultObj, "errmsg"), resultObj.get("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("提交失败");
    }

    /**
     * 获取订单列表
     */
    @ApiOperation(value = "取消订单")
    @PostMapping("cancelOrder")
    public Object cancelOrder(Integer orderId) {
        try {
            OrderDO orderVo = orderService.queryObject(orderId);
            if (orderVo.getOrder_status() == 300) {
                return toResponsFail("已发货，不能取消");
            } else if (orderVo.getOrder_status() == 301) {
                return toResponsFail("已收货，不能取消");
            }
            // 需要退款
            if (orderVo.getPay_status() == 2) {
                /*WechatRefundApiResult result = WechatUtil.wxRefund(orderVo.getId().toString(),
                        0.01, 0.01);
                if (result.getResult_code().equals("SUCCESS")) {
                    if (orderVo.getOrder_status() == 201) {
                        orderVo.setOrder_status(401);
                    } else if (orderVo.getOrder_status() == 300) {
                        orderVo.setOrder_status(402);
                    }
                    orderVo.setPay_status(4);
                    Order order=new Order();
                    BeanUtils.copyProperties(orderVo,order);
                    orderService.update(order);
                    return toResponsMsgSuccess("取消成功");
                } else {
                    return toResponsObject(400, "取消成失败", "");
                }*/
            } else {
                orderVo.setOrder_status(101);
                Order order=new Order();
                BeanUtils.copyProperties(orderVo,order);
                orderService.update(order);
                return toResponsSuccess("取消成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("提交失败");
    }

    /**
     * 确认收货
     */
    @ApiOperation(value = "确认收货")
    @PostMapping("confirmOrder")
    public Object confirmOrder(Integer orderId) {
        try {
            OrderDO orderVo = orderService.queryObject(orderId);
            orderVo.setOrder_status(301);
            orderVo.setShipping_status(2);
            orderVo.setConfirm_time(new Date());
            Order order=new Order();
            BeanUtils.copyProperties(orderVo,order);
            orderService.update(order);
            return toResponsSuccess("确认收货成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("提交失败");
    }
}