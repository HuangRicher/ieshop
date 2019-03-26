package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.SysRoleDO;

import java.util.List;
import java.util.Map;

public interface SysRoleExtMapper {

    List<SysRoleDO> queryList(Map<String,Object> map);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
