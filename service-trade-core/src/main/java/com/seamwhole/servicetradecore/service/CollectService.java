package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopCollect;

import java.util.List;
import java.util.Map;


public interface CollectService {

    ShopCollect queryObject(Integer id);

    List<ShopCollect> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int save(ShopCollect collect);

    void update(ShopCollect collect);

    int delete(Integer id);

    void deleteBatch(Integer[] ids);

}
