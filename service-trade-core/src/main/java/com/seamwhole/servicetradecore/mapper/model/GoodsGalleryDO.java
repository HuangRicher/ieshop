package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.GoodsGallery;

import java.io.Serializable;

public class GoodsGalleryDO extends GoodsGallery implements Serializable {

    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
