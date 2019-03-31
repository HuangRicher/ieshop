package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;

public class AddressDO extends ShopAddress implements Serializable{

    private String shopUserName;

    public String getShopUserName() {
        return shopUserName;
    }

    public void setShopUserName(String shopUserName) {
        this.shopUserName = shopUserName;
    }
}
