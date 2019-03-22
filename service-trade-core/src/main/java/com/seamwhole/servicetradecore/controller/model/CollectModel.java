package com.seamwhole.servicetradecore.controller.model;

import java.io.Serializable;

public class CollectModel implements Serializable {

    private Integer userId;
    private Integer typeId;
    private Integer valueId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }
}
