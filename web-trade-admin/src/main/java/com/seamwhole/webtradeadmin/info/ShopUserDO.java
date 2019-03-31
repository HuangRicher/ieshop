package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;

public class ShopUserDO extends ShopUser implements Serializable{

    private String levelName;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
