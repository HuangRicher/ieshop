package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.SysRegion;

import java.io.Serializable;

public class SysRegionDO extends SysRegion implements Serializable {

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
