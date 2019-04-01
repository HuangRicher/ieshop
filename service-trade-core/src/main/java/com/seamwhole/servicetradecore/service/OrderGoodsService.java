package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.OrderGoods;

import java.util.List;
import java.util.Map;


public interface OrderGoodsService {

    List<OrderGoods> queryList(Map<String, Object> map);

    void save(OrderGoods orderGoods);

    OrderGoods queryObject(Integer id);

    PageInfo<OrderGoods> queryByPage(Map<String, Object> params, Integer page, Integer limit);

    void update(OrderGoods orderGoods);

    void deleteBatch(Integer[] ids);
}
