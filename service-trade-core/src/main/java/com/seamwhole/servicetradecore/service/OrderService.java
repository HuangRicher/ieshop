package com.seamwhole.servicetradecore.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.OrderDO;
import com.seamwhole.servicetradecore.model.Order;
import com.seamwhole.servicetradecore.model.ShopUser;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderDO queryObject(Integer id);

    PageInfo<Order> findByPage(Integer userId,Integer pageNum,Integer pageSize,String order);

    void save(Order order);

    int update(Order order);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    Map<String, Object> submit(JSONObject jsonParam, ShopUser loginUser);
}
