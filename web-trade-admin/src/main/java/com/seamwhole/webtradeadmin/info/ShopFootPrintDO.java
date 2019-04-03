package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;

public class ShopFootPrintDO extends FootPrint implements Serializable {
    private String goodsName;
    private String userName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
