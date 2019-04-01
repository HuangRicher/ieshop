package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.RelatedGoods;
import com.seamwhole.webtradeadmin.service.RelatedGoodsService;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class RelatedGoodsServiceHystrix implements RelatedGoodsService {
    @Override
    public PagesInfo<RelatedGoods> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public RelatedGoods queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(RelatedGoods relatedGoods) {

    }

    @Override
    public void update(RelatedGoods relatedGoods) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }
}
