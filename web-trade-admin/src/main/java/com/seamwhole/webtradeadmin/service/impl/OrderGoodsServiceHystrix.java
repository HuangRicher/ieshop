package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.OrderGoods;
import com.seamwhole.webtradeadmin.service.OrderGoodsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrderGoodsServiceHystrix implements OrderGoodsService {

    @Override
    public PagesInfo<OrderGoods> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public void save(OrderGoods orderGoods) {

    }

    @Override
    public OrderGoods queryObject(Integer id) {
        return null;
    }

    @Override
    public void update(OrderGoods orderGoods) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<OrderGoods> queryList(Map<String, Object> params) {
        return null;
    }
}
