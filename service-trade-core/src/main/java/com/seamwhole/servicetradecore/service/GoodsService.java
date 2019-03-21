package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.Goods;

import java.util.List;
import java.util.Map;


public interface GoodsService {

    Goods queryObject(Integer id);

    List<Goods> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Goods goods);

    void update(Goods goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<Goods> queryHotGoodsList(Map<String, Object> map);

    List<Goods> queryCatalogProductList(Map<String, Object> map);
}
