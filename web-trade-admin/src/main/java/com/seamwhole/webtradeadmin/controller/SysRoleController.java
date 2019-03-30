package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.constant.Constant;
import com.seamwhole.webtradeadmin.info.SysRoleDO;
import com.seamwhole.webtradeadmin.info.SysRoleInfo;
import com.seamwhole.webtradeadmin.info.SysRoleModel;
import com.seamwhole.webtradeadmin.service.SysRoleService;
import com.seamwhole.webtradeadmin.shiro.ShiroUtils;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController{
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //如果不是超级管理员，则只查询自己创建的角色列表
        SysRoleModel roleModel=new SysRoleModel();
        if (ShiroUtils.getUserId() != Constant.SUPER_ADMIN) {
            roleModel.setCreateUserId(ShiroUtils.getUserId());
        }
        roleModel.setPageNum(Integer.valueOf((String)params.get("page")));
        roleModel.setPageSize(Integer.valueOf((String)params.get("limit")));
        //查询列表数据
        PagesInfo<SysRoleDO> page=sysRoleService.queryRoleByPage(roleModel);
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public ResponseObject select() {
        SysRoleModel roleModel=new SysRoleModel();

        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (ShiroUtils.getUserId() != Constant.SUPER_ADMIN) {
            roleModel.setCreateUserId(ShiroUtils.getUserId());
        }
        List<SysRoleDO> list = sysRoleService.queryRoleList(roleModel);

        return ResponseObject.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public ResponseObject info(@PathVariable("roleId") Long roleId) {
        SysRoleInfo info=sysRoleService.getRoleInfoById(roleId);
        return ResponseObject.ok().put("role", info);
    }

    /**
     * 保存角色
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    public ResponseObject save(@RequestBody SysRoleModel roleModel) {
        roleModel.setCreateUserId(ShiroUtils.getUserId());
        sysRoleService.saveRole(roleModel);
        return ResponseObject.ok();
    }

    /**
     * 修改角色
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:update")
    public ResponseObject update(@RequestBody SysRoleModel roleModel) {
        roleModel.setCreateUserId(ShiroUtils.getUserId());
        sysRoleService.updateRole(roleModel);
        return ResponseObject.ok();
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public ResponseObject delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteRoleBatch(roleIds);
        return ResponseObject.ok();
    }
}
