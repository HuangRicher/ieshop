package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.AttributeCategory;

import java.util.List;
import java.util.Map;

public interface AttributeCategoryService {
    PageInfo<AttributeCategory> queryByPage(Map<String, Object> params, Integer page, Integer limit);

    AttributeCategory getById(Integer id);

    void save(AttributeCategory attributeCategory);

    void updateById(AttributeCategory attributeCategory);

    void deleteBatch(Integer[] ids);

    List<AttributeCategory> queryList(Map<String, Object> params);
}
