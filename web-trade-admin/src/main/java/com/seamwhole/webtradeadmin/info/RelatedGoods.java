package com.seamwhole.webtradeadmin.info;

public class RelatedGoods {
    private Integer id;

    private Integer goodsId;

    private Integer relatedGoodsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getRelatedGoodsId() {
        return relatedGoodsId;
    }

    public void setRelatedGoodsId(Integer relatedGoodsId) {
        this.relatedGoodsId = relatedGoodsId;
    }
}