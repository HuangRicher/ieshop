package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.Goods;

import java.io.Serializable;

public class GoodsDO extends Goods implements Serializable {

    private Integer productId;
    private Integer orderNum;
    private Integer cartNum;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getCartNum() {
        return cartNum;
    }

    public void setCartNum(Integer cartNum) {
        this.cartNum = cartNum;
    }
}
