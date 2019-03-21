package com.seamwhole.servicetradecore.controller.model;

import java.io.Serializable;

public class BuyGoodsModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private Integer goodsId;
    private Integer productId;
    private Integer number;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
