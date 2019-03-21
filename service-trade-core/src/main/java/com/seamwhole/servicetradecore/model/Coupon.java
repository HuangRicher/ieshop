package com.seamwhole.servicetradecore.model;

import java.math.BigDecimal;
import java.util.Date;

public class Coupon {
    private Short id;

    private String name;

    private BigDecimal typeMoney;

    private Byte sendType;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private Date sendStartDate;

    private Date sendEndDate;

    private Date useStartDate;

    private Date useEndDate;

    private BigDecimal minGoodsAmount;

    private Integer minTransmitNum;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getTypeMoney() {
        return typeMoney;
    }

    public void setTypeMoney(BigDecimal typeMoney) {
        this.typeMoney = typeMoney;
    }

    public Byte getSendType() {
        return sendType;
    }

    public void setSendType(Byte sendType) {
        this.sendType = sendType;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Date getSendStartDate() {
        return sendStartDate;
    }

    public void setSendStartDate(Date sendStartDate) {
        this.sendStartDate = sendStartDate;
    }

    public Date getSendEndDate() {
        return sendEndDate;
    }

    public void setSendEndDate(Date sendEndDate) {
        this.sendEndDate = sendEndDate;
    }

    public Date getUseStartDate() {
        return useStartDate;
    }

    public void setUseStartDate(Date useStartDate) {
        this.useStartDate = useStartDate;
    }

    public Date getUseEndDate() {
        return useEndDate;
    }

    public void setUseEndDate(Date useEndDate) {
        this.useEndDate = useEndDate;
    }

    public BigDecimal getMinGoodsAmount() {
        return minGoodsAmount;
    }

    public void setMinGoodsAmount(BigDecimal minGoodsAmount) {
        this.minGoodsAmount = minGoodsAmount;
    }

    public Integer getMinTransmitNum() {
        return minTransmitNum;
    }

    public void setMinTransmitNum(Integer minTransmitNum) {
        this.minTransmitNum = minTransmitNum;
    }
}