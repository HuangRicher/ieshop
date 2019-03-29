package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.except.CheckException;
import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.constant.Constant;
import com.seamwhole.webtradeadmin.info.SysMenuInfo;
import com.seamwhole.webtradeadmin.info.SysMenuModel;
import com.seamwhole.webtradeadmin.service.SysUserRoleMenuService;
import com.seamwhole.webtradeadmin.shiro.ShiroUtils;
import com.seamwhole.webtradeadmin.shiro.SysMenu;
import com.seamwhole.webtradeadmin.shiro.SysMenuDO;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController{
    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<SysMenuDO> pageInfo=sysUserRoleMenuService.queryMenusByPage(params);
        return ResponseObject.ok().put("page", pageInfo);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<SysMenuDO> menuList = sysUserRoleMenuService.queryMenuList(params);

        return ResponseObject.ok().put("list", menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public ResponseObject select() {
        //查询列表数据
        List<SysMenuInfo> list = sysUserRoleMenuService.queryNotButtonMenuList();
        return ResponseObject.ok().put("menuList", list);
    }

    /**
     * 角色授权菜单
     */
    @RequestMapping("/perms")
    @RequiresPermissions("sys:menu:perms")
    public ResponseObject perms() {
        //查询列表数据
        List<SysMenuDO> menuList = sysUserRoleMenuService.queryMenuList();
        return ResponseObject.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public ResponseObject info(@PathVariable("menuId") Long menuId) {
        SysMenu menu = sysUserRoleMenuService.getMenuById(menuId);
        return ResponseObject.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public ResponseObject save(@RequestBody SysMenuModel menuModel) {
        //数据校验
        SysMenu menu=new SysMenu();
        menu.setName(menuModel.getName());
        menu.setParentId(menuModel.getParentId());
        verifyForm(menu);
        sysUserRoleMenuService.saveMenu(menu);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public ResponseObject update(@RequestBody SysMenuModel menuModel) {
        //数据校验
        SysMenu menu=new SysMenu();
        menu.setMenuId(menuModel.getMenuId());
        menu.setName(menuModel.getName());
        menu.setParentId(menuModel.getParentId());
        verifyForm(menu);
        sysUserRoleMenuService.updateMenuById(menu);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public ResponseObject delete(@RequestBody Long[] menuIds) {
        for (Long menuId : menuIds) {
            if (menuId.longValue() <= 30) {
                return ResponseObject.error("系统菜单，不能删除");
            }
        }
        sysUserRoleMenuService.deleteMenuBatch(menuIds);

        return ResponseObject.ok();
    }

    /**
     * 用户菜单列表
     */
    @RequestMapping("/user")
    public ResponseObject user() {
        List<SysMenuInfo> menuList = sysUserRoleMenuService.getUserMenuList(ShiroUtils.getUserEntity().getUserId());
        return ResponseObject.ok().put("menuList", menuList);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new CheckException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new CheckException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new CheckException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysUserRoleMenuService.getMenuById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new CheckException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new CheckException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
