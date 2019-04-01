package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;

public class SearchHistoryDO extends SearchHistory implements Serializable {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
