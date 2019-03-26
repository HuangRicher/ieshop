package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.SysRegionDO;

import java.util.List;
import java.util.Map;

public interface SysRegionExtMapper {

    List<SysRegionDO> queryList(Map<String,Object> map);
}
