package com.seamwhole.servicetradecore.mapper.model;

import com.seamwhole.servicetradecore.model.SysUser;

import java.io.Serializable;

public class SysUserDO extends SysUser implements Serializable {

    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
