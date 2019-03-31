package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.SysRoleModel;
import com.seamwhole.servicetradecore.domain.SysRoleInfo;
import com.seamwhole.servicetradecore.mapper.model.SysRoleDO;
import com.seamwhole.servicetradecore.model.SysRole;
import com.seamwhole.servicetradecore.service.SysRoleDeptService;
import com.seamwhole.servicetradecore.service.SysRoleMenuService;
import com.seamwhole.servicetradecore.service.SysRoleService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysRole")
public class SysRoleResource {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;



    @PostMapping("/queryRoleByPage")
    public PagesInfo<SysRoleDO> queryRoleByPage(@RequestBody SysRoleModel roleModel){
        Map<String, Object> params = new HashMap<>();
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (roleModel.getUserId() !=null && roleModel.getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", roleModel.getUserId());
        }
        //查询列表数据
        PageInfo<SysRoleDO> pageInfo=sysRoleService.queryByPage(params,roleModel.getPageNum(),roleModel.getPageSize());
        return new PagesInfo<>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @PostMapping("/queryRoleList")
    public List<SysRoleDO> queryRoleList(@RequestBody SysRoleModel roleModel){
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (roleModel.getUserId()!=null && roleModel.getUserId() != Constant.SUPER_ADMIN) {
            map.put("createUserId", roleModel.getUserId());
        }
        return sysRoleService.queryList(map);
    }


    @GetMapping("/getRoleInfoById/{roleId}")
    public SysRoleInfo getRoleInfoById(@PathVariable("roleId") Long roleId){
        SysRole role = sysRoleService.queryObject(roleId);
        SysRoleInfo info=new SysRoleInfo();
        BeanUtils.copyProperties(role,info);
        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        info.setMenuIdList(menuIdList);
        //查询角色对应的部门
        List<Long> deptIdList = sysRoleDeptService.queryDeptIdList(roleId);
        info.setDeptIdList(deptIdList);
        return info;
    }

    @PostMapping("/saveRole")
    public void saveRole(@RequestBody SysRoleModel roleModel){
        sysRoleService.save(roleModel);
    }

    @PostMapping("/updateRole")
    public void updateRole(@RequestBody SysRoleModel roleModel){
        sysRoleService.update(roleModel);
    }

    @PostMapping("/deleteRoleBatch")
    public void deleteRoleBatch(@RequestBody Long[] roleIds){
        sysRoleService.deleteBatch(roleIds);
    }
}
