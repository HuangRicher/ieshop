package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopTopic;

import java.util.List;
import java.util.Map;


public interface TopicService {

    ShopTopic queryObject(Integer id);

    List<ShopTopic> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(ShopTopic topic);

    void update(ShopTopic topic);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);
}
