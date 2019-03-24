package com.seamwhole.servicetradecore.controller.model;

import java.io.Serializable;

public class SearchModel implements Serializable{

    private Integer userId;
    private String keyword;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
