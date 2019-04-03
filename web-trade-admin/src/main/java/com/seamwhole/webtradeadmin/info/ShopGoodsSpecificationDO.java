package com.seamwhole.webtradeadmin.info;

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
