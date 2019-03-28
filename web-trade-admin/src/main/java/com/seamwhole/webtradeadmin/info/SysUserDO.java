package com.seamwhole.webtradeadmin.info;

import com.seamwhole.webtradeadmin.shiro.SysUser;

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
