package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopAddress;

import java.util.List;
import java.util.Map;


public interface AddressService {

    ShopAddress getById(Integer id);

    List<ShopAddress> queryListByUserId(Integer userId);

    int queryTotal(Map<String, Object> map);

    void save(ShopAddress address);

    void updateById(ShopAddress address);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
