package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.SysDept;

import java.io.Serializable;

public class SysDeptDO extends SysDept implements Serializable {

    private String parentName;

    private boolean open;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
