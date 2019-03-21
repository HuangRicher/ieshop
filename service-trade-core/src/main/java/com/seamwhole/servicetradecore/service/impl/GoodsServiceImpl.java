package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.GoodsMapper;
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


    public Goods queryObject(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }


    public List<Goods> queryList(Map<String, Object> map) {
        //return goodsMapper.queryList(map);
        return null;
    }


    public int queryTotal(Map<String, Object> map) {
        //return goodsMapper.queryTotal(map);
        return 0;
    }


    public void save(Goods goods) {
        goodsMapper.insertSelective(goods);
    }


    public void update(Goods goods) {
        //goodsMapper.updateById(goods);
    }


    public void delete(Integer id) {
        goodsMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        GoodsExample example=new GoodsExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        goodsMapper.deleteByExample(example);
    }

    public List<Goods> queryHotGoodsList(Map<String, Object> map) {
        //return goodsMapper.queryHotGoodsList(map);
        return null;
    }

    public List<Goods> queryCatalogProductList(Map<String, Object> map) {
        //return goodsMapper.queryCatalogProductList(map);
        return null;
    }
}
