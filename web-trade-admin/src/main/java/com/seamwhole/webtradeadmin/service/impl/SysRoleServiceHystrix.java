package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysRoleDO;
import com.seamwhole.webtradeadmin.info.SysRoleInfo;
import com.seamwhole.webtradeadmin.info.SysRoleModel;
import com.seamwhole.webtradeadmin.service.SysRoleService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysRoleServiceHystrix implements SysRoleService {
    @Override
    public PagesInfo<SysRoleDO> queryRoleByPage(SysRoleModel roleModel) {
        return null;
    }

    @Override
    public List<SysRoleDO> queryRoleList(SysRoleModel roleModel) {
        return null;
    }

    @Override
    public SysRoleInfo getRoleInfoById(Long roleId) {
        return null;
    }

    @Override
    public void saveRole(SysRoleModel roleModel) {

    }

    @Override
    public void updateRole(SysRoleModel roleModel) {

    }

    @Override
    public void deleteRoleBatch(Long[] roleIds) {

    }
}
