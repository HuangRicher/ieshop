package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class OrderDO extends Order implements Serializable {
    private String shippingCode;
    private String username;
    private Map handleOption; //可操作的选项
    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map getHandleOption() {
        handleOption = new HashMap();
        handleOption.put("cancel", false);//取消操作
        handleOption.put("delete", false);//删除操作
        handleOption.put("pay", false);//支付操作
        handleOption.put("comment", false);//评论操作
        handleOption.put("delivery", false);//确认收货操作
        handleOption.put("confirm", false);//完成订单操作
        handleOption.put("return", false); //退换货操作
        handleOption.put("buy", false); //再次购买

        //订单流程：　下单成功－》支付订单－》发货－》收货－》评论
        //订单相关状态字段设计，采用单个字段表示全部的订单状态
        //1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除
        //2xx 表示订单支付状态　201订单已付款，等待发货
        //3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
        //4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货

        //如果订单已经取消或是已完成，则可删除和再次购买
        if (getOrderStatus() == 101) {
//            handleOption.put("delete", true);
            handleOption.put("buy", true);
        }

        //如果订单没有被取消，且没有支付，则可支付，可取消
        if (getOrderStatus() == 0) {
            handleOption.put("cancel", true);
            handleOption.put("pay", true);
        }

        //如果订单已付款，没有发货，则可退款操作
        if (getOrderStatus() == 201) {
            handleOption.put("cancel", true);
        }

        //如果订单已经发货，没有收货，则可收货操作和退款、退货操作
        if (getOrderStatus() == 300) {
//            handleOption.put("cancel", true);
            handleOption.put("confirm", true);
//            handleOption.put("return", true);
        }

        //如果订单已经支付，且已经收货，则可完成交易、评论和再次购买
        if (getOrderStatus() == 301) {
            handleOption.put("comment", true);
            handleOption.put("buy", true);
        }
        return handleOption;
    }

    public void setHandleOption(Map handleOption) {
        this.handleOption = handleOption;
    }
}
