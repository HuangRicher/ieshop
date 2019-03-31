package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ShopAddressMapper;
import com.seamwhole.servicetradecore.mapper.ext.ShopAddressExtMapper;
import com.seamwhole.servicetradecore.mapper.model.AddressDO;
import com.seamwhole.servicetradecore.model.ShopAddress;
import com.seamwhole.servicetradecore.model.ShopAddressExample;
import com.seamwhole.servicetradecore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ShopAddressMapper shopAddressMapper;

    @Autowired
    private ShopAddressExtMapper shopAddressExtMapper;



    public ShopAddress getById(Integer id) {
        return shopAddressMapper.selectByPrimaryKey(id);
    }


    public List<ShopAddress> queryListByUserId(Integer userId) {
        ShopAddressExample example=new ShopAddressExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return shopAddressMapper.selectByExample(example);
    }

    @Override
    public List<AddressDO> queryList(Map<String, Object> params) {
        return shopAddressExtMapper.queryList(params);
    }

    @Override
    public PageInfo<AddressDO> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<AddressDO> page= PageHelper.startPage(pageNum,pageSize);
        shopAddressExtMapper.queryList(params);
        return page.toPageInfo();
    }

    public int queryTotal(Map<String, Object> map) {
        ShopAddressExample example=new ShopAddressExample();
        return shopAddressMapper.countByExample(example);
    }

    @Override
    public AddressDO queryObject(Integer id) {
        return shopAddressExtMapper.getById(id);
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
