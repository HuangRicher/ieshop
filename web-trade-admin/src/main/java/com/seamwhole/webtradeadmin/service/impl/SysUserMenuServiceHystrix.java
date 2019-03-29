package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysMenuInfo;
import com.seamwhole.webtradeadmin.info.SysUserDO;
import com.seamwhole.webtradeadmin.info.SysUserInfo;
import com.seamwhole.webtradeadmin.info.SysUserModel;
import com.seamwhole.webtradeadmin.service.SysUserRoleMenuService;
import com.seamwhole.webtradeadmin.shiro.SysMenu;
import com.seamwhole.webtradeadmin.shiro.SysMenuDO;
import com.seamwhole.webtradeadmin.shiro.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    public List<SysMenuDO> queryMenuList() {
        return null;
    }

    @Override
    public void updateSysUserStatus(SysUser user) {

    }

    @Override
    public PagesInfo<SysUserDO> queryUserByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        return 0;
    }

    @Override
    public SysUserInfo getUserInfoById(Long userId) {
        return null;
    }

    @Override
    public void saveUser(SysUserModel user) {

    }

    @Override
    public void updateUser(SysUserModel user) {

    }

    @Override
    public void deleteUserBatch(SysUserModel user) {

    }

    @Override
    public PagesInfo<SysMenuDO> queryMenusByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<SysMenuDO> queryMenuList(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<SysMenuInfo> queryNotButtonMenuList() {
        return null;
    }

    @Override
    public SysMenu getMenuById(Long menuId) {
        return null;
    }

    @Override
    public void saveMenu(SysMenu menu) {

    }

    @Override
    public void updateMenuById(SysMenu menu) {

    }

    @Override
    public void deleteMenuBatch(Long[] menuIds) {

    }

    @Override
    public List<SysMenuInfo> getUserMenuList(Long userId) {
        return null;
    }
}
