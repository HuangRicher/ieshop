package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.ShopAddressMapper;
import com.seamwhole.servicetradecore.model.ShopAddress;
import com.seamwhole.servicetradecore.model.ShopAddressExample;
import com.seamwhole.servicetradecore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class ApiAddressService implements AddressService {

    @Autowired
    private ShopAddressMapper shopAddressMapper;


    public ShopAddress getById(Integer id) {
        return shopAddressMapper.selectByPrimaryKey(id);
    }


    public List<ShopAddress> queryListByUserId(Integer userId) {
        ShopAddressExample example=new ShopAddressExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return shopAddressMapper.selectByExample(example);
    }


    public int queryTotal(Map<String, Object> map) {
        ShopAddressExample example=new ShopAddressExample();
        return shopAddressMapper.countByExample(example);
    }


    public void save(ShopAddress address) {
        shopAddressMapper.insertSelective(address);
    }


    public void updateById(ShopAddress address) {
        shopAddressMapper.updateByPrimaryKeySelective(address);
    }


    public void delete(Integer id) {
        shopAddressMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        ShopAddressExample example=new ShopAddressExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        shopAddressMapper.deleteByExample(example);
    }

}
