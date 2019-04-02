package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ShopChannelMapper;
import com.seamwhole.servicetradecore.model.ShopChannel;
import com.seamwhole.servicetradecore.model.ShopChannelExample;
import com.seamwhole.servicetradecore.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ShopChannelMapper shopChannelMapper;


    public List<ShopChannel> queryList(Map<String, Object> map) {
        ShopChannelExample example=new ShopChannelExample();
        example.setOrderByClause(" sort_order asc ");
        return shopChannelMapper.selectByExample(example);
    }

    @Override
    public PageInfo<ShopChannel> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        ShopChannelExample example=new ShopChannelExample();
        example.setOrderByClause(" sort_order asc ");
        Page<ShopChannel> page= PageHelper.startPage(pageNum,pageSize);
        shopChannelMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public ShopChannel getById(Integer id) {
        return shopChannelMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(ShopChannel channel) {
        shopChannelMapper.insertSelective(channel);
    }

    @Override
    public void update(ShopChannel channel) {
        shopChannelMapper.updateByPrimaryKeySelective(channel);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        ShopChannelExample example=new ShopChannelExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        shopChannelMapper.deleteByExample(example);
    }
}
