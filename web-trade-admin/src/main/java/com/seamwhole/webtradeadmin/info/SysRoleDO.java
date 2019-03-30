package com.seamwhole.webtradeadmin.info;

import java.io.Serializable;

public class SysRoleDO extends SysRole implements Serializable {

    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
