package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.mapper.model.GoodsDO;
import com.seamwhole.servicetradecore.model.Goods;

import java.util.List;
import java.util.Map;


public interface GoodsService {

    Goods queryObject(Integer id);

    List<GoodsDO> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Goods goods);

    void update(Goods goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<GoodsDO> queryHotGoodsList(Map<String, Object> map);

    List<GoodsDO> queryCatalogProductList(Map<String, Object> map);
}
