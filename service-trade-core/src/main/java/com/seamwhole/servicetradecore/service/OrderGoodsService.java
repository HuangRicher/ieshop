package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.OrderGoods;

import java.util.List;
import java.util.Map;


public interface OrderGoodsService {

    List<OrderGoods> queryList(Map<String, Object> map);
}
