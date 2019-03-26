package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.mapper.model.GoodsSpecificationDO;
import com.seamwhole.servicetradecore.model.GoodsSpecification;

import java.util.List;
import java.util.Map;


public interface GoodsSpecificationService {

    GoodsSpecification queryObject(Integer id);

    List<GoodsSpecificationDO> queryList(Map<String, Object> map);

    void save(GoodsSpecification goods);

    void update(GoodsSpecification goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
