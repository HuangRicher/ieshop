package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.SysDeptDO;

import java.util.List;
import java.util.Map;

public interface SysDeptExtMapper {

    List<SysDeptDO> queryList(Map<String,Object> map);

    /**
     * 查询子部门ID列表
     *
     * @param parentId 上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);
}
