package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.CollectDO;

import java.util.List;
import java.util.Map;

public interface ShopCollectExtMapper {

    List<CollectDO> queryList(Map<String, Object> map);
}
