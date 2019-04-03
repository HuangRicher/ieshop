package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.GoodsSpecification;

import java.io.Serializable;

public class ShopGoodsSpecificationDO extends GoodsSpecification implements Serializable {

    private String goodsName;
    private String specificationName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }
}
