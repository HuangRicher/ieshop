package com.seamwhole.servicefarmplan.model;

public class PlanRequirementsItem {
    private String itemId;

    private String planId;

    private Integer feedId;

    private Float weight;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}