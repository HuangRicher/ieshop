package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.SysSmsLog;

import java.io.Serializable;

public class SysSmsLogDO extends SysSmsLog implements Serializable {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
