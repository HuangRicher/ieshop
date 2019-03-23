package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.OrderGoodsMapper;
import com.seamwhole.servicetradecore.model.OrderGoods;
import com.seamwhole.servicetradecore.model.OrderGoodsExample;
import com.seamwhole.servicetradecore.service.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    public List<OrderGoods> queryList(Map<String, Object> map) {
        OrderGoodsExample example=new OrderGoodsExample();
        if(map.get("orderId")!=null)
            example.createCriteria().andOrderIdEqualTo((int)map.get("orderId"));
        return orderGoodsMapper.selectByExample(example);
    }

}
