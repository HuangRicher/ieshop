package com.seamwhole.webtradeadmin.info;

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
