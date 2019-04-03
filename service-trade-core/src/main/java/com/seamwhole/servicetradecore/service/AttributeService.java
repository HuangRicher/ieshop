package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopAttributeDO;
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

    PageInfo<ShopAttributeDO> queryShopAdByPage(Map<String, Object> params, Integer page, Integer limit);

    List<ShopAttributeDO> queryShopList(Map<String, Object> params);
}
