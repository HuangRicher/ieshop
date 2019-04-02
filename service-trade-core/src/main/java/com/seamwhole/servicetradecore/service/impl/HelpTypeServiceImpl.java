package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.HelpTypeMapper;
import com.seamwhole.servicetradecore.model.HelpType;
import com.seamwhole.servicetradecore.model.HelpTypeExample;
import com.seamwhole.servicetradecore.service.HelpTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class HelpTypeServiceImpl implements HelpTypeService {

    @Autowired
    private HelpTypeMapper helpTypeMapper;


    public List<HelpType> queryList(Map<String, Object> map) {
        HelpTypeExample example=new HelpTypeExample();
        if(map.get("typeName")!=null)
            example.createCriteria().andTypeNameLike("%"+(String)map.get("typeName")+"%");
        return helpTypeMapper.selectByExample(example);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        HelpTypeExample example=new HelpTypeExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        helpTypeMapper.deleteByExample(example);
    }

    @Override
    public void updateById(HelpType helpType) {
        helpTypeMapper.updateByPrimaryKeySelective(helpType);
    }

    @Override
    public void save(HelpType helpType) {
        helpTypeMapper.insertSelective(helpType);
    }

    @Override
    public HelpType getById(Integer id) {
        return helpTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<HelpType> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<HelpType> page= PageHelper.startPage(pageNum,pageSize);
        HelpTypeExample example=new HelpTypeExample();
        if(params.get("name")!=null)
            example.createCriteria().andTypeNameLike("%"+params.get("name")+"%");
        helpTypeMapper.selectByExample(example);
        return page.toPageInfo();
    }
}
