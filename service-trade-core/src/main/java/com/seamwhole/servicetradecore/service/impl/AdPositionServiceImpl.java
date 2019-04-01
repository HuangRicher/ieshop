package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.AdPositionMapper;
import com.seamwhole.servicetradecore.model.AdPosition;
import com.seamwhole.servicetradecore.model.AdPositionExample;
import com.seamwhole.servicetradecore.service.AdPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class AdPositionServiceImpl implements AdPositionService {

    @Autowired
    private AdPositionMapper adPositionMapper;


    @Override
    public PageInfo<AdPosition> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<AdPosition> page= PageHelper.startPage(pageNum,pageSize);
        AdPositionExample example=new AdPositionExample();
        if(params.get("name")!=null)
            example.createCriteria().andNameLike("%"+params.get("name")+"%");
        adPositionMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public AdPosition getById(Integer id) {
        return adPositionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(AdPosition adPosition) {
        adPositionMapper.insertSelective(adPosition);
    }

    @Override
    public void updateById(AdPosition adPosition) {
        adPositionMapper.updateByPrimaryKeySelective(adPosition);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        AdPositionExample example=new AdPositionExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        adPositionMapper.deleteByExample(example);
    }

    @Override
    public List<AdPosition> queryList(Map<String, Object> params) {
        AdPositionExample example=new AdPositionExample();
        if(params.get("name")!=null)
            example.createCriteria().andNameLike("%"+params.get("name")+"%");
        return adPositionMapper.selectByExample(example);
    }
}
