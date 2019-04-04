package com.seamwhole.servicetradecore.domain;

import com.seamwhole.servicetradecore.mapper.model.OrderDO;

import java.io.Serializable;

public class OrderInfo extends OrderDO implements Serializable{
    private Integer goodsCount;

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
}
