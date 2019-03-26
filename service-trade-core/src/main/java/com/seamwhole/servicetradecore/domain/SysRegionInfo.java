package com.seamwhole.servicetradecore.domain;

import com.seamwhole.servicetradecore.mapper.model.SysRegionDO;

import java.io.Serializable;

public class SysRegionInfo extends SysRegionDO implements Serializable {

    private String value;
    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
