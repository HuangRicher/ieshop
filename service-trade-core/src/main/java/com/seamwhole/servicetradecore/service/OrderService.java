package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.OrderDO;
import com.seamwhole.servicetradecore.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderDO queryObject(Integer id);

    PageInfo<Order> findByPage(Integer userId,Integer pageNum,Integer pageSize,String order);

    void save(Order order);

    int update(Order order);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    Map<String, Object> submit(Integer couponId,String type,String postscript,Integer addressId,Integer userId);

    PageInfo<OrderDO> queryShopOrderByPage(Map<String, Object> params, Integer page, Integer limit);

    OrderDO getShopOrderById(Integer id);

    List<OrderDO> queryShopOrderList(Map<String, Object> params);

    int getTotalCount(Map<String, Object> params);

    void confirm(Integer id);

    void sendGoods(Order order);
}
