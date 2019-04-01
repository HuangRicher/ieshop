package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.RelatedGoods;

import java.util.List;
import java.util.Map;


public interface RelatedGoodsService {

    List<RelatedGoods> queryList(Map<String, Object> map);

    void deleteBatch(Integer[] ids);

    PageInfo<RelatedGoods> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);

    RelatedGoods getById(Integer id);

    void save(RelatedGoods relatedGoods);

    void updateById(RelatedGoods relatedGoods);
}
