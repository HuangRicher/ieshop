package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.webtradeadmin.service.SysUserRoleMenuService;
import com.seamwhole.webtradeadmin.shiro.SysMenuDO;
import com.seamwhole.webtradeadmin.shiro.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysUserMenuServiceHystrix implements SysUserRoleMenuService {
    @Override
    public SysUser queryByUserName(String username) {
        return null;
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return null;
    }

    @Override
    public List<SysMenuDO> queryList() {
        return null;
    }

    @Override
    public void updateSysUserStatus(SysUser user) {

    }
}
