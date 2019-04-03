package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.GoodsSpecificationMapper;
import com.seamwhole.servicetradecore.mapper.ext.GoodsSpecificationExtMapper;
import com.seamwhole.servicetradecore.mapper.model.GoodsSpecificationDO;
import com.seamwhole.servicetradecore.mapper.model.ShopGoodsSpecificationDO;
import com.seamwhole.servicetradecore.model.GoodsSpecification;
import com.seamwhole.servicetradecore.model.GoodsSpecificationExample;
import com.seamwhole.servicetradecore.service.GoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService {

    @Autowired
    private GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    private GoodsSpecificationExtMapper goodsSpecificationExtMapper;

    public GoodsSpecification queryObject(Integer id) {
        return goodsSpecificationMapper.selectByPrimaryKey(id);
    }


    public List<GoodsSpecificationDO> queryList(Map<String, Object> map) {
        return goodsSpecificationExtMapper.queryList(map);
    }


    public void save(GoodsSpecification goods) {
        goodsSpecificationMapper.insertSelective(goods);
    }


    public void update(GoodsSpecification goods) {
        goodsSpecificationMapper.updateByPrimaryKeySelective(goods);
    }


    public void delete(Integer id) {
        goodsSpecificationMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        GoodsSpecificationExample example=new GoodsSpecificationExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        goodsSpecificationMapper.deleteByExample(example);
    }

    @Override
    public List<ShopGoodsSpecificationDO> queryShopList(Map<String, Object> params) {
        return goodsSpecificationExtMapper.queryShopList(params);
    }

    @Override
    public PageInfo<ShopGoodsSpecificationDO> queryShopByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<ShopGoodsSpecificationDO> page= PageHelper.startPage(pageNum,pageSize);
        goodsSpecificationExtMapper.queryShopList(params);
        return page.toPageInfo();
    }
}
