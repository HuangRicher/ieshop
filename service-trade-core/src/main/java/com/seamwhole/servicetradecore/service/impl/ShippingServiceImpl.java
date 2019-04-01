package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ShopShippingMapper;
import com.seamwhole.servicetradecore.model.ShopShipping;
import com.seamwhole.servicetradecore.model.ShopShippingExample;
import com.seamwhole.servicetradecore.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    private ShopShippingMapper shopShippingMapper;


    @Override
    public PageInfo<ShopShipping> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<ShopShipping> page= PageHelper.startPage(pageNum,pageSize);
        ShopShippingExample example=new ShopShippingExample();
        shopShippingMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public ShopShipping getById(Integer id) {
        return shopShippingMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(ShopShipping shipping) {
        shopShippingMapper.insertSelective(shipping);
    }

    @Override
    public void updateById(ShopShipping shipping) {
        shopShippingMapper.updateByPrimaryKeySelective(shipping);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        ShopShippingExample example=new ShopShippingExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        shopShippingMapper.deleteByExample(example);
    }

    @Override
    public List<ShopShipping> queryList(Map<String, Object> params) {
        ShopShippingExample example=new ShopShippingExample();
        return shopShippingMapper.selectByExample(example);
    }
}
