package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.SpecificationMapper;
import com.seamwhole.servicetradecore.model.Specification;
import com.seamwhole.servicetradecore.model.SpecificationExample;
import com.seamwhole.servicetradecore.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;


    @Override
    public PageInfo<Specification> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<Specification> page= PageHelper.startPage(pageNum,pageSize);
        SpecificationExample example=new SpecificationExample();
        specificationMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public Specification getById(Integer id) {
        return specificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Specification specification) {
        specificationMapper.insertSelective(specification);
    }

    @Override
    public void updateById(Specification specification) {
        specificationMapper.updateByPrimaryKeySelective(specification);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        SpecificationExample example=new SpecificationExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        specificationMapper.deleteByExample(example);
    }

    @Override
    public List<Specification> queryList(Map<String, Object> params) {
        SpecificationExample example=new SpecificationExample();
        return specificationMapper.selectByExample(example);
    }
}
