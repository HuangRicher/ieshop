package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopChannel;

import java.util.List;
import java.util.Map;


public interface ChannelService {

    ShopChannel queryObject(Integer id);

    List<ShopChannel> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ShopChannel order);

    void update(ShopChannel order);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
