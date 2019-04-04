package com.seamwhole.webtradeadmin.info;

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
