package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.RelatedGoods;

import java.util.List;
import java.util.Map;


public interface RelatedGoodsService {

    RelatedGoods queryObject(Integer id);

    List<RelatedGoods> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryhasPicTotal(Map<String, Object> map);

    int save(RelatedGoods comment);

    void update(RelatedGoods comment);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
