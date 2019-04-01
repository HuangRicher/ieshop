package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ShopShipping;

import java.util.List;
import java.util.Map;

public interface ShippingService {
    PageInfo<ShopShipping> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);

    ShopShipping getById(Integer id);

    void save(ShopShipping shipping);

    void updateById(ShopShipping shipping);

    void deleteBatch(Integer[] ids);

    List<ShopShipping> queryList(Map<String, Object> params);
}
