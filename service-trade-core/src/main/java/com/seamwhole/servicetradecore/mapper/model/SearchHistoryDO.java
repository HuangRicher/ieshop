package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.SearchHistory;

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
