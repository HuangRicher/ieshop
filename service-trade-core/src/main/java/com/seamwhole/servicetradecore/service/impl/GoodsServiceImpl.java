package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.GoodsMapper;
import com.seamwhole.servicetradecore.mapper.ext.GoodsExtMapper;
import com.seamwhole.servicetradecore.mapper.model.GoodsDO;
import com.seamwhole.servicetradecore.model.Goods;
import com.seamwhole.servicetradecore.model.GoodsExample;
import com.seamwhole.servicetradecore.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsExtMapper goodsExtMapper;




    public Goods queryObject(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }


    public List<GoodsDO> queryList(Map<String, Object> map) {
        return goodsExtMapper.queryList(map);
    }

    @Override
    public PageInfo<GoodsDO> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        Page<GoodsDO> page=PageHelper.startPage(pageNum,pageSize);
        goodsExtMapper.queryList(map);
        return page.toPageInfo();
    }

    public int queryTotal(Map<String, Object> map) {
        return goodsExtMapper.queryTotal(map);
    }


    public void save(Goods goods) {
        goodsMapper.insertSelective(goods);
    }


    public void update(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }


    public void delete(Integer id) {
        goodsMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        GoodsExample example=new GoodsExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        goodsMapper.deleteByExample(example);
    }

    public List<GoodsDO> queryHotGoodsList(Map<String, Object> map) {
        return goodsExtMapper.queryHotGoodsList(map);
    }

    public List<GoodsDO> queryCatalogProductList(Map<String, Object> map) {
        return goodsExtMapper.queryCatalogProductList(map);
    }

    @Override
    public PageInfo<GoodsDO> queryCatalogProductByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        Page<GoodsDO> page= PageHelper.startPage(pageNum,pageSize);
        goodsExtMapper.queryCatalogProductList(map);
        return page.toPageInfo();
    }
}
