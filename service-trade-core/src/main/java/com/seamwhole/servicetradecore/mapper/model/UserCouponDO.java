package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.UserCoupon;

import java.io.Serializable;

public class UserCouponDO extends UserCoupon implements Serializable {

    private String userName;
    private String couponName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}
