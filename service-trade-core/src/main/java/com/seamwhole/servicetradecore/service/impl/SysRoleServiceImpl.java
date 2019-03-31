package com.seamwhole.servicetradecore.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.SysRoleModel;
import com.seamwhole.servicetradecore.mapper.SysRoleMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysRoleExtMapper;
import com.seamwhole.servicetradecore.mapper.model.SysRoleDO;
import com.seamwhole.servicetradecore.model.SysRole;
import com.seamwhole.servicetradecore.service.SysRoleDeptService;
import com.seamwhole.servicetradecore.service.SysRoleMenuService;
import com.seamwhole.servicetradecore.service.SysRoleService;
import com.seamwhole.servicetradecore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleExtMapper sysRoleExtMapper;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    @Override
    public SysRole queryObject(Long roleId) {
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<SysRoleDO> queryList(Map<String, Object> map) {
        return sysRoleExtMapper.queryList(map);
    }

    @Override
    public PageInfo<SysRoleDO> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        Page<SysRoleDO> page= PageHelper.startPage(pageNum,pageSize);
        sysRoleExtMapper.queryList(map);
        return page.toPageInfo();
    }


    @Override
    @Transactional
    public void save(SysRoleModel roleModel) {
        SysRole role=new SysRole();
        role.setCreateUserId(roleModel.getCreateUserId());
        role.setDeptId(roleModel.getDeptId());
        role.setRoleName(roleModel.getRoleName());
        role.setRemark(roleModel.getRemark());
        role.setCreateTime(new Date());
        sysRoleMapper.insertSelective(role);

        //检查权限是否越权
        checkPrems(roleModel.getCreateUserId(),roleModel.getMenuIdList());

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), roleModel.getMenuIdList());

        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getRoleId(), roleModel.getDeptIdList());
    }

    @Override
    @Transactional
    public void update(SysRoleModel roleModel) {
        SysRole role=new SysRole();
        role.setRoleId(roleModel.getRoleId());
        role.setCreateUserId(roleModel.getCreateUserId());
        role.setDeptId(roleModel.getDeptId());
        role.setRoleName(roleModel.getRoleName());
        role.setRemark(roleModel.getRemark());
        sysRoleMapper.updateByPrimaryKeySelective(role);

        //检查权限是否越权
        checkPrems(roleModel.getCreateUserId(),roleModel.getMenuIdList());

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(roleModel.getRoleId(), roleModel.getMenuIdList());
        //保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(roleModel.getRoleId(), roleModel.getDeptIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        sysRoleExtMapper.deleteBatch(roleIds);
    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return sysRoleExtMapper.queryRoleIdList(createUserId);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(Long createUserId,List<Long> menuIdLists) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (createUserId == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(createUserId);

        //判断是否越权
        if (!menuIdList.containsAll(menuIdLists)) {
            throw new CheckException("新增角色的权限，已超出你的权限范围");
        }
    }

    /*@Override
    public Page<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        sysRoleDao.queryPageByDto(userWindowDto);
        return PageHelper.endPage();
    }*/
}
