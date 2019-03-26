package com.seamwhole.servicetradecore.domain;

import com.seamwhole.servicetradecore.mapper.model.SysUserDO;

import java.io.Serializable;
import java.util.List;

public class SysUserInfo extends SysUserDO implements Serializable {
    private List<Long> roleIdList;

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
