package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.ShopCart;

import java.math.BigDecimal;

public class ShopCartDO extends ShopCart {

    private BigDecimal retailProductPrice;
    private String userName;

    public BigDecimal getRetailProductPrice() {
        return retailProductPrice;
    }

    public void setRetailProductPrice(BigDecimal retailProductPrice) {
        this.retailProductPrice = retailProductPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
