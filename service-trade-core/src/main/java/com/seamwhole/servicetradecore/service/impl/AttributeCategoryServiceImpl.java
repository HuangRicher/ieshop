package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.AttributeCategoryMapper;
import com.seamwhole.servicetradecore.model.AttributeCategory;
import com.seamwhole.servicetradecore.model.AttributeCategoryExample;
import com.seamwhole.servicetradecore.service.AttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class AttributeCategoryServiceImpl implements AttributeCategoryService {
    @Autowired
    private AttributeCategoryMapper attributeCategoryMapper;


    @Override
    public PageInfo<AttributeCategory> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        AttributeCategoryExample example=new AttributeCategoryExample();
        AttributeCategoryExample.Criteria criteria=example.createCriteria();
        if(params.get("name")!=null)
            criteria.andNameLike("%"+params.get("name")+"%");
        if(params.get("enabled")!=null)
            criteria.andEnabledEqualTo((int)params.get("enabled"));
        Page<AttributeCategory> page= PageHelper.startPage(pageNum,pageSize);
        attributeCategoryMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public AttributeCategory getById(Integer id) {
        return attributeCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(AttributeCategory attributeCategory) {
        attributeCategoryMapper.insertSelective(attributeCategory);
    }

    @Override
    public void updateById(AttributeCategory attributeCategory) {
        attributeCategoryMapper.updateByPrimaryKeySelective(attributeCategory);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        AttributeCategoryExample example=new AttributeCategoryExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        attributeCategoryMapper.deleteByExample(example);
    }

    @Override
    public List<AttributeCategory> queryList(Map<String, Object> params) {
        AttributeCategoryExample example=new AttributeCategoryExample();
        AttributeCategoryExample.Criteria criteria=example.createCriteria();
        if(params.get("name")!=null)
            criteria.andNameLike("%"+params.get("name")+"%");
        if(params.get("enabled")!=null)
            criteria.andEnabledEqualTo((int)params.get("enabled"));
        return attributeCategoryMapper.selectByExample(example);
    }
}
