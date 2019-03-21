package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopAd;

import java.util.List;
import java.util.Map;


public interface AdService {

    ShopAd queryObject(Integer id);

    List<ShopAd> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ShopAd brand);

    void update(ShopAd brand);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
