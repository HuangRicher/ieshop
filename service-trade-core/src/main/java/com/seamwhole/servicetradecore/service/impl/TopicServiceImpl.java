package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ShopTopicMapper;
import com.seamwhole.servicetradecore.model.ShopTopic;
import com.seamwhole.servicetradecore.model.ShopTopicExample;
import com.seamwhole.servicetradecore.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private ShopTopicMapper shopTopicMapper;


    public ShopTopic queryObject(Integer id) {
        return shopTopicMapper.selectByPrimaryKey(id);
    }


    public List<ShopTopic> queryList(Map<String, Object> map) {
        ShopTopicExample example=new ShopTopicExample();
        return shopTopicMapper.selectByExample(example);
    }

    @Override
    public PageInfo<ShopTopic> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        ShopTopicExample example=new ShopTopicExample();
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()->shopTopicMapper.selectByExample(example));
    }

    @Override
    public void save(ShopTopic topic) {
        shopTopicMapper.insertSelective(topic);
    }

    @Override
    public void updateById(ShopTopic topic) {
        shopTopicMapper.updateByPrimaryKeySelective(topic);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        ShopTopicExample example=new ShopTopicExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        shopTopicMapper.deleteByExample(example);
    }
}
