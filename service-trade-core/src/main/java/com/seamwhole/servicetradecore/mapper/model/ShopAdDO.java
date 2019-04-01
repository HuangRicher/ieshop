package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.ShopAd;

import java.io.Serializable;

public class ShopAdDO extends ShopAd implements Serializable {

    private String adPositionName;

    public String getAdPositionName() {
        return adPositionName;
    }

    public void setAdPositionName(String adPositionName) {
        this.adPositionName = adPositionName;
    }
}
