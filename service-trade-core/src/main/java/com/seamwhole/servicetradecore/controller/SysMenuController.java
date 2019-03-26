package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.SysMenuModel;
import com.seamwhole.servicetradecore.domain.SysMenuInfo;
import com.seamwhole.servicetradecore.mapper.model.SysMenuDO;
import com.seamwhole.servicetradecore.model.SysMenu;
import com.seamwhole.servicetradecore.service.SysMenuService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController{
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    public ResponseObject list(@RequestBody SysMenuModel menuModel) {
        //查询列表数据
        Map<String, Object> params=new HashMap<>();
        PageInfo<SysMenuDO> pageInfo=sysMenuService.queryByPage(params,menuModel.getPageNum(),menuModel.getPageSize());
        return ResponseObject.ok().put("page", pageInfo);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestBody SysMenuModel menuModel) {
        //查询列表数据
        Map<String, Object> params=new HashMap<>();
        List<SysMenuDO> menuList = sysMenuService.queryList(params);
        return ResponseObject.ok().put("list", menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    public ResponseObject select() {
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();
        List<SysMenuInfo> list=new ArrayList<>();
        for(SysMenu menu:menuList){
            SysMenuInfo info=new SysMenuInfo();
            BeanUtils.copyProperties(menu,info);
            list.add(info);
        }

        //添加顶级菜单
        SysMenuInfo root = new SysMenuInfo();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        list.add(root);

        return ResponseObject.ok().put("menuList", list);
    }

    /**
     * 角色授权菜单
     */
    @RequestMapping("/perms")
    public ResponseObject perms(@RequestBody SysMenuModel menuModel) {
        //查询列表数据
        List<SysMenuDO> menuList = null;

        //只有超级管理员，才能查看所有管理员列表
        if (menuModel.getUserId() == Constant.SUPER_ADMIN) {
            menuList = sysMenuService.queryList(new HashMap<String, Object>());
        } else {
            menuList = sysMenuService.queryUserList(menuModel.getUserId());
        }

        return ResponseObject.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    public ResponseObject info(@PathVariable("menuId") Long menuId) {
        SysMenu menu = sysMenuService.queryObject(menuId);
        return ResponseObject.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseObject save(@RequestBody SysMenuModel menuModel) {
        //数据校验
        //verifyForm(menu);
        SysMenu menu=new SysMenu();
        menu.setName(menuModel.getName());
        menu.setParentId(menuModel.getParentId());
        sysMenuService.save(menu);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseObject update(@RequestBody SysMenuModel menuModel) {
        //数据校验
        //verifyForm(menu);
        SysMenu menu=new SysMenu();
        menu.setMenuId(menuModel.getMenuId());
        menu.setName(menuModel.getName());
        menu.setParentId(menuModel.getParentId());
        sysMenuService.update(menu);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseObject delete(@RequestBody SysMenuModel menuModel) {
        for (Long menuId : menuModel.getMenuIds()) {
            if (menuId.longValue() <= 30) {
                return ResponseObject.error("系统菜单，不能删除");
            }
        }
        sysMenuService.deleteBatch(menuModel.getMenuIds());
        return ResponseObject.ok();
    }

    /**
     * 用户菜单列表
     */
    @RequestMapping("/user")
    public ResponseObject user(@RequestBody SysMenuModel menuModel) {
        List<SysMenuInfo> menuList = sysMenuService.getUserMenuList(menuModel.getUserId());
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
            SysMenu parentMenu = sysMenuService.queryObject(menu.getParentId());
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
