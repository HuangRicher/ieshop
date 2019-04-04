package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Order;
import com.seamwhole.webtradeadmin.info.OrderDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrderServiceHystrix implements OrderService {
    @Override
    public PagesInfo<OrderDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public OrderDO queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<OrderDO> queryList(Map<String, Object> params) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> params) {
        return 0;
    }

    @Override
    public void confirm(Integer id) {

    }

    @Override
    public void sendGoods(Order order) {

    }
}
