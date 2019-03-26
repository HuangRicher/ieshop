package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.SysRoleModel;
import com.seamwhole.servicetradecore.mapper.model.SysRoleDO;
import com.seamwhole.servicetradecore.model.SysRole;

import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface SysRoleService {

    SysRole queryObject(Long roleId);

    List<SysRoleDO> queryList(Map<String, Object> map);

    PageInfo<SysRoleDO> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize);

    void save(SysRoleModel roleModel);

    void update(SysRoleModel roleModel);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);

    /**
     * 分页查询角色审批选择范围
     * @return
     */
    //Page<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto, int pageNmu);
}
