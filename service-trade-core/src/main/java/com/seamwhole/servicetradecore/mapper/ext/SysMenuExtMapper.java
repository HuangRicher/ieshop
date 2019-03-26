package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.SysMenuDO;

import java.util.List;
import java.util.Map;

public interface SysMenuExtMapper {

    List<SysMenuDO> queryList(Map<String,Object> map);

    int deleteBatch(Long[] id);

    /**
     * 查询用户的权限列表
     */
    List<SysMenuDO> queryUserList(Long userId);
}
