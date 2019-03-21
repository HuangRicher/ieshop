package com.seamwhole.serviceuser.model;

import java.math.BigDecimal;

public class Wallet {
    private String userId;

    private BigDecimal money;

    private Integer consumePoint;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getConsumePoint() {
        return consumePoint;
    }

    public void setConsumePoint(Integer consumePoint) {
        this.consumePoint = consumePoint;
    }
}