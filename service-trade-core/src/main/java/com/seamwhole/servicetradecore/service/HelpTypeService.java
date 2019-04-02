package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.HelpType;

import java.util.List;
import java.util.Map;

public interface HelpTypeService {

    List<HelpType> queryList(Map<String, Object> map);

    void deleteBatch(Integer[] ids);

    void updateById(HelpType helpType);

    void save(HelpType helpType);

    HelpType getById(Integer id);

    PageInfo<HelpType> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);
}
