package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.SearchHistoryDO;

import java.util.List;
import java.util.Map;

public interface SearchHistoryExtMapper {

    List<SearchHistoryDO> queryList(Map<String,Object> map);
}
