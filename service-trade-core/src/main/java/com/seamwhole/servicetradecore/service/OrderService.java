package com.seamwhole.servicetradecore.service;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.servicetradecore.model.Order;
import com.seamwhole.servicetradecore.model.ShopUser;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Order queryObject(Integer id);

    List<Order> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Order order);

    int update(Order order);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    Map<String, Object> submit(JSONObject jsonParam, ShopUser loginUser);
}
