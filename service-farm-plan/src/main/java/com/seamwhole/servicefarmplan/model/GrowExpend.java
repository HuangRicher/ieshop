package com.seamwhole.servicefarmplan.model;

public class GrowExpend {
    private Integer id;

    private Integer productId;

    private Integer dayCount;

    private String expendDetail;

    private Float weight;

    private Float addWeight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public String getExpendDetail() {
        return expendDetail;
    }

    public void setExpendDetail(String expendDetail) {
        this.expendDetail = expendDetail == null ? null : expendDetail.trim();
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getAddWeight() {
        return addWeight;
    }

    public void setAddWeight(Float addWeight) {
        this.addWeight = addWeight;
    }
}