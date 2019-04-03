package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.GoodsAttributeMapper;
import com.seamwhole.servicetradecore.model.GoodsAttribute;
import com.seamwhole.servicetradecore.model.GoodsAttributeExample;
import com.seamwhole.servicetradecore.service.GoodsAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class GoodsAttributeServiceImpl implements GoodsAttributeService {

    @Autowired
    private GoodsAttributeMapper goodsAttributeMapper;


    @Override
    public PageInfo<GoodsAttribute> queryShopByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        GoodsAttributeExample example=new GoodsAttributeExample();
        Page<GoodsAttribute> page= PageHelper.startPage(pageNum,pageSize);
        goodsAttributeMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public GoodsAttribute getById(Integer id) {
        return goodsAttributeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(GoodsAttribute goodsAttribute) {
        goodsAttributeMapper.insertSelective(goodsAttribute);
    }

    @Override
    public void updateById(GoodsAttribute goodsAttribute) {
        goodsAttributeMapper.updateByPrimaryKeyWithBLOBs(goodsAttribute);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        GoodsAttributeExample example=new GoodsAttributeExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        goodsAttributeMapper.deleteByExample(example);
    }
}
