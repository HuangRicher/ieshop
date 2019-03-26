package com.seamwhole.servicetradecore.mapper.ext;

import java.util.List;
import java.util.Map;

public interface SysRoleDeptExtMapper {

    int insertBatch(Map<String,Object> map);

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> queryDeptIdList(Long roleId);

    /**
     * 根据用户ID获取权限部门列表
     *
     * @param userId
     * @return
     */
    List<Long> queryDeptIdListByUserId(Long userId);
}
