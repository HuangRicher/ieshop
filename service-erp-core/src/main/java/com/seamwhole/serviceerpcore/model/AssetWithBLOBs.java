package com.seamwhole.serviceerpcore.model;

public class AssetWithBLOBs extends Asset {
    private String description;

    private String addmonth;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAddmonth() {
        return addmonth;
    }

    public void setAddmonth(String addmonth) {
        this.addmonth = addmonth == null ? null : addmonth.trim();
    }
}