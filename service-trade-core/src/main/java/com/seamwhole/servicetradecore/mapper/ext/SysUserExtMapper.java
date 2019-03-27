package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.SysUserDO;

import java.util.List;
import java.util.Map;

public interface SysUserExtMapper {

    List<SysUserDO> queryList(Map<String,Object> param);

    int deleteBatch(Long[] id);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    List<String> queryAllPerms(Long userId);
}
