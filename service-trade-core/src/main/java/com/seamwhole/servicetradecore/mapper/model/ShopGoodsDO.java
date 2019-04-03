package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.Goods;

import java.io.Serializable;

public class ShopGoodsDO extends Goods implements Serializable {
    private String categoryName;
    private String attributeCategoryName;
    private String brandName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAttributeCategoryName() {
        return attributeCategoryName;
    }

    public void setAttributeCategoryName(String attributeCategoryName) {
        this.attributeCategoryName = attributeCategoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
