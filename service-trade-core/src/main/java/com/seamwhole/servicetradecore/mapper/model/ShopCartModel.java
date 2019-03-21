package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.ShopCart;

import java.math.BigDecimal;

public class ShopCartModel extends ShopCart {

    private BigDecimal retailProductPrice;

    public BigDecimal getRetailProductPrice() {
        return retailProductPrice;
    }

    public void setRetailProductPrice(BigDecimal retailProductPrice) {
        this.retailProductPrice = retailProductPrice;
    }
}
