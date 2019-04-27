package com.seamwhole.servicetradecore.domain;

import com.seamwhole.servicetradecore.mapper.model.OrderDO;
import com.seamwhole.servicetradecore.model.Goods;
import com.seamwhole.servicetradecore.model.OrderGoods;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.List;

public class OrderInfo extends OrderDO implements Serializable{
    private Integer goodsCount;
    private List<OrderGoods> goodsList;
    private String fullRegion;
    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public List<OrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public String getFullRegion() {
        if (StringUtils.isEmpty(fullRegion)) {
            fullRegion =getProvince() + getCity() + getDistrict();
        }
        return fullRegion;
    }

    public void setFullRegion(String fullRegion) {
        this.fullRegion = fullRegion;
    }
}
