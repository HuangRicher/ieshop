package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.RelatedGoodsMapper;
import com.seamwhole.servicetradecore.model.RelatedGoods;
import com.seamwhole.servicetradecore.model.RelatedGoodsExample;
import com.seamwhole.servicetradecore.service.RelatedGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class RelatedGoodsServiceImpl implements RelatedGoodsService {

    @Autowired
    private RelatedGoodsMapper RelatedGoodsMapper;

    public List<RelatedGoods> queryList(Map<String, Object> map) {
        RelatedGoodsExample example=new RelatedGoodsExample();
        return RelatedGoodsMapper.selectByExample(example);
    }


}
