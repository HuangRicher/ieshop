package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {
    PageInfo<Specification> queryByPage(Map<String, Object> params, Integer page, Integer limit);

    Specification getById(Integer id);

    void save(Specification specification);

    void updateById(Specification specification);

    void deleteBatch(Integer[] ids);

    List<Specification> queryList(Map<String, Object> params);
}
