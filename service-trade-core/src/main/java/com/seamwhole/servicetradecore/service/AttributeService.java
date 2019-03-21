package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopAttribute;

import java.util.List;
import java.util.Map;


public interface AttributeService {

    ShopAttribute queryObject(Integer id);

    List<ShopAttribute> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ShopAttribute goods);

    void update(ShopAttribute goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
