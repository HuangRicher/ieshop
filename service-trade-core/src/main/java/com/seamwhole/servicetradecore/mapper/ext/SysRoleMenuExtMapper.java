package com.seamwhole.servicetradecore.mapper.ext;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuExtMapper {

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    void insertBatch(Map<String, Object> map);
}
