package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.GoodsAttribute;

import java.util.Map;

public interface GoodsAttributeService {
    PageInfo<GoodsAttribute> queryShopByPage(Map<String, Object> params, Integer page, Integer limit);

    GoodsAttribute getById(Integer id);

    void save(GoodsAttribute goodsAttribute);

    void updateById(GoodsAttribute goodsAttribute);

    void deleteBatch(Integer[] ids);
}
