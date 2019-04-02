package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;


public class OrderDO extends Order implements Serializable {
    private String shippingCode;
    private String username;

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
