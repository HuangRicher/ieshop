package com.seamwhole.webtradeadmin.info;

import com.seamwhole.webtradeadmin.shiro.SysUser;

import java.io.Serializable;
import java.util.List;

public class SysUserInfo extends SysUser implements Serializable {
    private List<Long> roleIdList;

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
