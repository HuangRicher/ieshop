package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ShopTopic;

import java.util.List;
import java.util.Map;


public interface TopicService {

    ShopTopic queryObject(Integer id);

    List<ShopTopic> queryList(Map<String, Object> map);

    PageInfo<ShopTopic> queryByPage(Map<String, Object> map,Integer pageNum,Integer pageSize);

    void save(ShopTopic topic);

    void updateById(ShopTopic topic);

    void deleteBatch(Integer[] ids);
}
