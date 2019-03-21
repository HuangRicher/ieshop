package com.seamwhole.servicetradecore.model;

public class FootPrint {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private Integer addTime;

    private Integer referrer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getReferrer() {
        return referrer;
    }

    public void setReferrer(Integer referrer) {
        this.referrer = referrer;
    }
}