package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.AdPosition;

import java.util.List;
import java.util.Map;

public interface AdPositionService {
    PageInfo<AdPosition> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);

    AdPosition getById(Integer id);

    void save(AdPosition adPosition);

    void updateById(AdPosition adPosition);

    void deleteBatch(Integer[] ids);

    List<AdPosition> queryList(Map<String, Object> params);
}
