package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.HelpType;

import java.util.List;
import java.util.Map;

public interface HelpTypeService {

    HelpType queryObject(Integer id);

    List<HelpType> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int save(HelpType helpType);

    int update(HelpType helpType);

    int delete(Integer id);

    int deleteBatch(Integer[] ids);
}
