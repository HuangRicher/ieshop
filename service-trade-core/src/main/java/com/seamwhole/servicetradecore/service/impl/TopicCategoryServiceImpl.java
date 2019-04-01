package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.TopicCategoryMapper;
import com.seamwhole.servicetradecore.model.TopicCategory;
import com.seamwhole.servicetradecore.model.TopicCategoryExample;
import com.seamwhole.servicetradecore.service.TopicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TopicCategoryServiceImpl implements TopicCategoryService {

    @Autowired
    private TopicCategoryMapper topicCategoryMapper;


    @Override
    public PageInfo<TopicCategory> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<TopicCategory> page= PageHelper.startPage(pageNum,pageSize);
        TopicCategoryExample example=new TopicCategoryExample();
        if(params.get("title")!=null)
            example.createCriteria().andTitleLike("%"+params.get("title")+"%");
        topicCategoryMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public TopicCategory getById(Integer id) {
        return topicCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(TopicCategory topicCategory) {
        topicCategoryMapper.insertSelective(topicCategory);
    }

    @Override
    public void updateById(TopicCategory topicCategory) {
        topicCategoryMapper.updateByPrimaryKeySelective(topicCategory);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        TopicCategoryExample example=new TopicCategoryExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        topicCategoryMapper.deleteByExample(example);
    }

    @Override
    public List<TopicCategory> queryList(Map<String, Object> params) {
        TopicCategoryExample example=new TopicCategoryExample();
        if(params.get("title")!=null)
            example.createCriteria().andTitleLike("%"+params.get("title")+"%");
        return topicCategoryMapper.selectByExample(example);
    }
}
