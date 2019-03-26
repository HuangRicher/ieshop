package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.SysRoleModel;
import com.seamwhole.servicetradecore.domain.SysRoleInfo;
import com.seamwhole.servicetradecore.mapper.model.SysRoleDO;
import com.seamwhole.servicetradecore.model.SysRole;
import com.seamwhole.servicetradecore.service.SysRoleDeptService;
import com.seamwhole.servicetradecore.service.SysRoleMenuService;
import com.seamwhole.servicetradecore.service.SysRoleService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController{
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    public ResponseObject list(@RequestBody SysRoleModel roleModel) {
        Map<String, Object> params = new HashMap<>();
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (roleModel.getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", roleModel.getUserId());
        }
        //查询列表数据
        PageInfo<SysRoleDO> pageInfo=sysRoleService.queryByPage(params,roleModel.getPageNum(),roleModel.getPageSize());
        return ResponseObject.ok().put("page", pageInfo);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    public ResponseObject select(@RequestBody SysRoleModel roleModel) {
        Map<String, Object> map = new HashMap<>();

        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (roleModel.getUserId() != Constant.SUPER_ADMIN) {
            map.put("createUserId", roleModel.getUserId());
        }
        List<SysRoleDO> list = sysRoleService.queryList(map);
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    public ResponseObject info(@PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.queryObject(roleId);
        SysRoleInfo info=new SysRoleInfo();
        BeanUtils.copyProperties(role,info);
        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        info.setMenuIdList(menuIdList);

        //查询角色对应的部门
        List<Long> deptIdList = sysRoleDeptService.queryDeptIdList(roleId);
        info.setDeptIdList(deptIdList);

        return ResponseObject.ok().put("role", info);
    }

    /**
     * 保存角色
     */
    @RequestMapping("/save")
    public ResponseObject save(@RequestBody SysRoleModel roleModel) {
        sysRoleService.save(roleModel);
        return ResponseObject.ok();
    }

    /**
     * 修改角色
     */
    @RequestMapping("/update")
    public ResponseObject update(@RequestBody SysRoleModel roleModel) {
        sysRoleService.update(roleModel);
        return ResponseObject.ok();
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    public ResponseObject delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);

        return ResponseObject.ok();
    }
}
