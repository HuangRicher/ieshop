package com.seamwhole.servicetradecore.mapper.ext;

import java.util.List;
import java.util.Map;

public interface SysUserRoleExtMapper {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    void insertBatch(Map<String,Object> map);
}
