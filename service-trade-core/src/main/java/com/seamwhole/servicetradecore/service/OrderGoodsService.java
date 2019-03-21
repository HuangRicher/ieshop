package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.OrderGoods;

import java.util.List;
import java.util.Map;


public interface OrderGoodsService {

    OrderGoods queryObject(Integer id);

    List<OrderGoods> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(OrderGoods order);

    void update(OrderGoods order);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
