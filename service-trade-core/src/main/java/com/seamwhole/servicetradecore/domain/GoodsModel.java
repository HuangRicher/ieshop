package com.seamwhole.servicetradecore.domain;

import com.seamwhole.servicetradecore.model.Goods;
import com.seamwhole.servicetradecore.model.GoodsAttribute;
import com.seamwhole.servicetradecore.model.GoodsGallery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsModel extends Goods implements Serializable {

    List<GoodsAttribute> attributeEntityList = new ArrayList<>();

    List<GoodsGallery> goodsImgList = new ArrayList<>();


    public List<GoodsAttribute> getAttributeEntityList() {
        return attributeEntityList;
    }

    public void setAttributeEntityList(List<GoodsAttribute> attributeEntityList) {
        this.attributeEntityList = attributeEntityList;
    }

    public List<GoodsGallery> getGoodsImgList() {
        return goodsImgList;
    }

    public void setGoodsImgList(List<GoodsGallery> goodsImgList) {
        this.goodsImgList = goodsImgList;
    }
}
