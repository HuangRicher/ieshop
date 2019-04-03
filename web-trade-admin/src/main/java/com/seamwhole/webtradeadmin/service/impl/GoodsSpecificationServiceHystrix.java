package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.GoodsSpecification;
import com.seamwhole.webtradeadmin.info.ShopGoodsSpecificationDO;
import com.seamwhole.webtradeadmin.service.GoodsSpecificationService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GoodsSpecificationServiceHystrix implements GoodsSpecificationService {
    @Override
    public List<ShopGoodsSpecificationDO> queryList(Map<String, Object> params) {
        return null;
    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public void update(GoodsSpecification goodsSpecification) {

    }

    @Override
    public void save(GoodsSpecification goodsSpecification) {

    }

    @Override
    public GoodsSpecification queryObject(Integer id) {
        return null;
    }

    @Override
    public PagesInfo<ShopGoodsSpecificationDO> queryByPage(Map<String, Object> params) {
        return null;
    }
}
