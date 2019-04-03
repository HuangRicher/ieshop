package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;

public class ShopCommentDO extends ShopComment implements Serializable{

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
