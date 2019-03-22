package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.GoodsSpecificationDO;

import java.util.List;
import java.util.Map;

public interface GoodsSpecificationExtMapper {

    List<GoodsSpecificationDO> queryList(Map<String, Object> map);
}