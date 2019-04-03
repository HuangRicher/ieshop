package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;

public class ShopAttributeDO extends ShopAttribute implements Serializable {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
