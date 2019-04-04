package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.OrderGoodsMapper;
import com.seamwhole.servicetradecore.model.OrderGoods;
import com.seamwhole.servicetradecore.model.OrderGoodsExample;
import com.seamwhole.servicetradecore.service.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public void save(OrderGoods orderGoods) {
        orderGoodsMapper.insertSelective(orderGoods);
    }

    @Override
    public OrderGoods queryObject(Integer id) {
        return orderGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<OrderGoods> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        OrderGoodsExample example=new OrderGoodsExample();
        if(params.get("orderId")!=null)
            example.createCriteria().andOrderIdEqualTo((int)params.get("orderId"));
        Page<OrderGoods> page= PageHelper.startPage(pageNum,pageSize);
        orderGoodsMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public void update(OrderGoods orderGoods) {
        orderGoodsMapper.updateByPrimaryKeyWithBLOBs(orderGoods);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        OrderGoodsExample example=new OrderGoodsExample();
        example.createCriteria().andOrderIdIn(Arrays.asList(ids));
        orderGoodsMapper.deleteByExample(example);
    }

}
