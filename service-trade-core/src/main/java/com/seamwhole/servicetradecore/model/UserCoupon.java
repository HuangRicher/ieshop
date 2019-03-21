package com.seamwhole.servicetradecore.model;

import java.util.Date;

public class UserCoupon {
    private Integer id;

    private Byte couponId;

    private String couponNumber;

    private Integer userId;

    private Date usedTime;

    private Date addTime;

    private Integer orderId;

    private String sourceKey;

    private Integer referrer;

    private Byte couponStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getCouponId() {
        return couponId;
    }

    public void setCouponId(Byte couponId) {
        this.couponId = couponId;
    }

    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber == null ? null : couponNumber.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey == null ? null : sourceKey.trim();
    }

    public Integer getReferrer() {
        return referrer;
    }

    public void setReferrer(Integer referrer) {
        this.referrer = referrer;
    }

    public Byte getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Byte couponStatus) {
        this.couponStatus = couponStatus;
    }
}