package com.seamwhole.servicetradecore.controller;

import com.seamwhole.constant.RedisKeyConstant;
import com.seamwhole.servicetradecore.annotation.LoginUser;
import com.seamwhole.servicetradecore.controller.model.OrderModel;
import com.seamwhole.servicetradecore.mapper.model.OrderDO;
import com.seamwhole.servicetradecore.model.Order;
import com.seamwhole.servicetradecore.model.OrderGoods;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.redis.RedisService;
import com.seamwhole.servicetradecore.service.OrderGoodsService;
import com.seamwhole.servicetradecore.service.OrderService;
import com.seamwhole.servicetradecore.util.CharUtil;
import com.seamwhole.servicetradecore.util.ResourceUtil;
import com.seamwhole.servicetradecore.wx.WXPayRequest;
import com.seamwhole.servicetradecore.wx.WXPayUtil;
import com.seamwhole.servicetradecore.wx.WXPayXmlUtil;
import com.seamwhole.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;


@Api(tags = "商户支付")
@RestController
@RequestMapping("/api/pay")
public class PayController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private RedisService redisService;


    /**
     */
    @ApiOperation(value = "跳转")
    @PostMapping("index")
    public Object index() {
        //
        return toResponsSuccess("");
    }

    /**
     * 使用钱包支付
     */
    @ApiOperation(value = "获取支付的请求参数")
    @PostMapping("payByWallet")
    public Object payByWallet(@LoginUser ShopUser loginUser,@RequestBody OrderModel orderModel){


        Map resultObj = null;
        try {
            resultObj =orderService.payByWallet(orderModel.getCouponId(),orderModel.getType(),orderModel.getPostscript(),orderModel.getAddressId(),loginUser.getId());
            //resultObj = orderService.submit(orderModel.getCouponId(),orderModel.getType(),orderModel.getPostscript(),orderModel.getAddressId(),loginUser.getId());
            if (null != resultObj) {
                return toResponsObject(MapUtils.getInteger(resultObj, "errno"), MapUtils.getString(resultObj, "errmsg"), resultObj.get("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObj;
    }



    /**
     * 获取支付的请求参数
     */
    /*@ApiOperation(value = "获取支付的请求参数")
    @PostMapping("prepay")
    public Object payPrepay(@LoginUser ShopUser loginUser, Integer orderId, HttpServletRequest request) {
        //
        OrderDO orderInfo = orderService.queryObject(orderId);

        if (null == orderInfo) {
            return toResponsObject(400, "订单已取消", "");
        }

        if (orderInfo.getPayStatus() != 0) {
            return toResponsObject(400, "订单已支付，请不要重复操作", "");
        }

        String nonceStr = CharUtil.getRandomString(32);

        //https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=3
        Map<String, String> resultObj = new TreeMap();

        try {
            Map<String, String> parame = new HashMap<>();
            parame.put("appid", ResourceUtil.getConfigByName("wx.appId"));
            // 商家账号。
            parame.put("mch_id", ResourceUtil.getConfigByName("wx.mchId"));
            String randomStr = CharUtil.getRandomNum(18).toUpperCase();
            // 随机字符串
            parame.put("nonce_str", randomStr);
            // 商户订单编号
            parame.put("out_trade_no", orderInfo.getOrderSn());
            Map orderGoodsParam = new HashMap();
            orderGoodsParam.put("order_id", orderId);
            // 商品描述
            parame.put("body", "超市-支付");
            //订单的商品
            List<OrderGoods> orderGoods = orderGoodsService.queryList(orderGoodsParam);
            if (null != orderGoods) {
                String body = "超市-";
                for (OrderGoods goodsVo : orderGoods) {
                    body = body + goodsVo.getGoodsName() + "、";
                }
                if (body.length() > 0) {
                    body = body.substring(0, body.length() - 1);
                }
                // 商品描述
                parame.put("body", body);
            }
            //支付金额
            parame.put("total_fee", orderInfo.getActualPrice().multiply(new BigDecimal(100)).intValue()+"");
            // 回调地址
            parame.put("notify_url", ResourceUtil.getConfigByName("wx.notifyUrl"));
            // 交易类型APP
            parame.put("trade_type", ResourceUtil.getConfigByName("wx.tradeType"));
            parame.put("spbill_create_ip", getClientIp(request));
            parame.put("openid", loginUser.getWeixinOpenid());
            String sign = WXPayUtil.generateSignature(parame, ResourceUtil.getConfigByName("wx.paySignKey"));
            // 数字签证
            parame.put("sign", sign);

            String xml = WXPayUtil.mapToXml(parame);
            logger.info("xml:" + xml);
            Map<String, String> resultUn = WXPayUtil.xmlToMap(new WXPayRequest().requestWithoutCert(ResourceUtil.getConfigByName("wx.uniformorder"), xml));
            // 响应报文
            String return_code = resultUn.get("return_code");
            String return_msg = resultUn.get("return_msg");
            //
            if (return_code.equalsIgnoreCase("FAIL")) {
                return toResponsFail("支付失败," + return_msg);
            } else if (return_code.equalsIgnoreCase("SUCCESS")) {
                // 返回数据
                String result_code = resultUn.get("result_code");
                String err_code_des = resultUn.get("err_code_des");
                if (result_code.equalsIgnoreCase("FAIL")) {
                    return toResponsFail("支付失败," + err_code_des);
                } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                    String prepay_id = resultUn.get("prepay_id");
                    // 先生成paySign 参考https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=7_7&index=5
                    resultObj.put("appId", ResourceUtil.getConfigByName("wx.appId"));
                    resultObj.put("timeStamp", DateUtils.timeToStr(System.currentTimeMillis() / 1000, DateUtils.DATE_TIME_PATTERN));
                    resultObj.put("nonceStr", nonceStr);
                    resultObj.put("package", "prepay_id=" + prepay_id);
                    resultObj.put("signType", "MD5");
                    String paySign = WXPayUtil.generateSignature(resultObj, ResourceUtil.getConfigByName("wx.paySignKey"));
                    resultObj.put("paySign", paySign);
                    // 业务处理
                    orderInfo.setPayId(prepay_id);
                    // 付款中
                    orderInfo.setPayStatus(1);
                    orderService.update(orderInfo);
                    return toResponsObject(0, "微信统一订单下单成功", resultObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return toResponsFail("下单失败,error=" + e.getMessage());
        }
        return toResponsFail("下单失败");
    }*/

    /**
     * 微信查询订单状态
     */
    /*@ApiOperation(value = "查询订单状态")
    @PostMapping("query")
    public Object orderQuery(ShopUser loginUser, Integer orderId) throws Exception {
        if (orderId == null) {
            return toResponsFail("订单不存在");
        }

        OrderDO orderDetail = orderService.queryObject(orderId);
        Map<String, String> parame = new TreeMap<>();
        parame.put("appid", ResourceUtil.getConfigByName("wx.appId"));
        // 商家账号。
        parame.put("mch_id", ResourceUtil.getConfigByName("wx.mchId"));
        String randomStr = CharUtil.getRandomNum(18).toUpperCase();
        // 随机字符串
        parame.put("nonce_str", randomStr);
        // 商户订单编号
        parame.put("out_trade_no", orderDetail.getOrderSn());

        String sign = WXPayUtil.generateSignature(parame, ResourceUtil.getConfigByName("wx.paySignKey"));
        // 数字签证
        parame.put("sign", sign);

        String xml = WXPayUtil.mapToXml(parame);
        logger.info("xml:" + xml);
        Map<String, String> resultUn = null;
        try {
            resultUn = WXPayUtil.xmlToMap(WechatUtil.requestOnce(ResourceUtil.getConfigByName("wx.orderquery"), xml));
        } catch (Exception e) {
            e.printStackTrace();
            return toResponsFail("查询失败,error=" + e.getMessage());
        }
        // 响应报文
        String return_code = resultUn.get("return_code");
        String return_msg = resultUn.get("return_msg");

        if (!"SUCCESS".equals(return_code)) {
            return toResponsFail("查询失败,error=" + return_msg);
        }

        String trade_state = resultUn.get("trade_state");
        if ("SUCCESS".equals(trade_state)) {
            // 更改订单状态
            // 业务处理
            Order orderInfo = new Order();
            orderInfo.setId(orderId);
            orderInfo.setPayStatus(2);
            orderInfo.setOrderStatus(201);
            orderInfo.setShippingStatus(0);
            orderInfo.setPayTime(new Date());
            orderService.update(orderInfo);
            return toResponsMsgSuccess("支付成功");
        } else if ("USERPAYING".equals(trade_state)) {
            // 重新查询 正在支付中
            Integer num = (Integer) redisService.get(RedisKeyConstant.SHOP_CACHE_NAME+"_queryRepeatNum_" + orderId);
            if (num == null) {
                redisService.set(RedisKeyConstant.SHOP_CACHE_NAME+"_queryRepeatNum_" + orderId, 1);
                this.orderQuery(loginUser, orderId);
            } else if (num <= 3) {
                redisService.remove(RedisKeyConstant.SHOP_CACHE_NAME+"_queryRepeatNum_" + orderId);
                this.orderQuery(loginUser, orderId);
            } else {
                return toResponsFail("查询失败,error=" + trade_state);
            }

        } else {
            // 失败
            return toResponsFail("查询失败,error=" + trade_state);
        }

        return toResponsFail("查询失败，未知错误");
    }*/

    /**
     * 微信订单回调接口
     *
     * @return
     */
    /*@ApiOperation(value = "微信订单回调接口")
    @RequestMapping(value = "/notify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            InputStream in = request.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
            //xml数据
            String reponseXml = new String(out.toByteArray(), "utf-8");

            WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.xmlStrToBean(reponseXml, WechatRefundApiResult.class);
            String result_code = result.getResult_code();
            if (result_code.equalsIgnoreCase("FAIL")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                logger.error("订单" + out_trade_no + "支付失败");
                response.getWriter().write(setXml("SUCCESS", "OK"));
            } else if (result_code.equalsIgnoreCase("SUCCESS")) {
                //订单编号
                String out_trade_no = result.getOut_trade_no();
                logger.error("订单" + out_trade_no + "支付成功");
                // 业务处理
                OrderDO orderInfo = orderService.queryObject(Integer.valueOf(out_trade_no));
                orderInfo.setPayStatus(2);
                orderInfo.setOrderStatus(201);
                orderInfo.setShippingStatus(0);
                orderInfo.setPayTime(new Date());
                Order order=new Order();
                BeanUtils.copyProperties(orderInfo,order);
                orderService.update(order);
                response.getWriter().write(setXml("SUCCESS", "OK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }*/

    /**
     * 订单退款请求
     */
    /*@ApiOperation(value = "订单退款请求")
    @PostMapping("refund")
    public Object refund(Integer orderId) {
        //
        OrderDO orderInfo = orderService.queryObject(orderId);

        if (null == orderInfo) {
            return toResponsObject(400, "订单已取消", "");
        }

        if (orderInfo.getOrderStatus() == 401 || orderInfo.getOrderStatus() == 402) {
            return toResponsObject(400, "订单已退款", "");
        }

//        if (orderInfo.getPay_status() != 2) {
//            return toResponsObject(400, "订单未付款，不能退款", "");
//        }

//        WechatRefundApiResult result = WechatUtil.wxRefund(orderInfo.getId().toString(),
//                orderInfo.getActual_price().doubleValue(), orderInfo.getActual_price().doubleValue());
        WechatRefundApiResult result = WechatUtil.wxRefund(orderInfo.getId().toString(),
                10.01, 10.01);
        if (result.getResult_code().equals("SUCCESS")) {
            if (orderInfo.getOrderStatus() == 201) {
                orderInfo.setOrderStatus(401);
            } else if (orderInfo.getOrderStatus() == 300) {
                orderInfo.setOrderStatus(402);
            }
            orderService.update(orderInfo);
            return toResponsObject(400, "成功退款", "");
        } else {
            return toResponsObject(400, "退款失败", "");
        }
    }*/


    //返回微信服务
    public static String setXml(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }

    //模拟微信回调接口
    public static String callbakcXml(String orderNum) {
        return "<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type> <is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid> <out_trade_no><![CDATA[" + orderNum + "]]></out_trade_no>  <result_code><![CDATA[SUCCESS]]></result_code> <return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id> <time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
    }

    /**
     * 获取请求方IP
     *
     * @return 客户端Ip
     */
    public String getClientIp(HttpServletRequest request) {
        String xff = request.getHeader("X-Real-IP");
        if(xff!=null) {
            return xff;
        }
        xff = request.getHeader("x-forwarded-for");
        if (xff == null) {
            return "8.8.8.8";
        }
        return xff;
    }
}