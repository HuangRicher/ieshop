package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.RelatedGoodsMapper;
import com.seamwhole.servicetradecore.model.RelatedGoods;
import com.seamwhole.servicetradecore.model.RelatedGoodsExample;
import com.seamwhole.servicetradecore.service.RelatedGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class RelatedGoodsServiceImpl implements RelatedGoodsService {

    @Autowired
    private RelatedGoodsMapper relatedGoodsMapper;

    public List<RelatedGoods> queryList(Map<String, Object> map) {
        RelatedGoodsExample example=new RelatedGoodsExample();
        return relatedGoodsMapper.selectByExample(example);
    }


    @Override
    public void deleteBatch(Integer[] ids) {
        RelatedGoodsExample example=new RelatedGoodsExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        relatedGoodsMapper.deleteByExample(example);
    }

    @Override
    public PageInfo<RelatedGoods> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<RelatedGoods> page= PageHelper.startPage(pageNum,pageSize);
        RelatedGoodsExample example=new RelatedGoodsExample();
        relatedGoodsMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public RelatedGoods getById(Integer id) {
        return relatedGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(RelatedGoods relatedGoods) {
        relatedGoodsMapper.insertSelective(relatedGoods);
    }

    @Override
    public void updateById(RelatedGoods relatedGoods) {
        relatedGoodsMapper.updateByPrimaryKeySelective(relatedGoods);
    }
}
