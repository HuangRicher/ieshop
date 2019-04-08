package com.seamwhole.webtradeadmin.info;

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
