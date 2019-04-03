package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.ShopCollect;

import java.io.Serializable;

public class ShopCollectDO extends ShopCollect implements Serializable {

    private String userName;

    private String valueName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
