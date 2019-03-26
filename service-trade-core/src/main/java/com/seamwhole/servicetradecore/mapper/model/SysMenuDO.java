package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.SysMenu;

import java.io.Serializable;

public class SysMenuDO extends SysMenu implements Serializable {

    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
