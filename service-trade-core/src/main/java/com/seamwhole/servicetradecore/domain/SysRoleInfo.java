package com.seamwhole.servicetradecore.domain;

import com.seamwhole.servicetradecore.mapper.model.SysRoleDO;

import java.io.Serializable;
import java.util.List;

public class SysRoleInfo extends SysRoleDO implements Serializable {

    private List<Long> menuIdList;

    private List<Long> deptIdList;

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public List<Long> getDeptIdList() {
        return deptIdList;
    }

    public void setDeptIdList(List<Long> deptIdList) {
        this.deptIdList = deptIdList;
    }
}
