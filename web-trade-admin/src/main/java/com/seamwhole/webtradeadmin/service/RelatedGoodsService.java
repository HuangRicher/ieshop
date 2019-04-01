package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.RelatedGoods;

import java.util.Map;

public interface RelatedGoodsService {
    PagesInfo<RelatedGoods> queryByPage(Map<String, Object> params);

    RelatedGoods queryObject(Integer id);

    void save(RelatedGoods relatedGoods);

    void update(RelatedGoods relatedGoods);

    void deleteBatch(Integer[] ids);
}
