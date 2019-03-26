package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.SysSmsLogDO;

import java.util.List;
import java.util.Map;

public interface SysSmsLogExtMapper {

    List<SysSmsLogDO> queryList(Map<String,Object> map);
}
