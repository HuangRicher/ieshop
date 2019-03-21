package com.seamwhole.servicetradecore.controller.model;

public class CartModel {

    private Integer userId;
    private String productIds;
    private Integer isChecked;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }
}
