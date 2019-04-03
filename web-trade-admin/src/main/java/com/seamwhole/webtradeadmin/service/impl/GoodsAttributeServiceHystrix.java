package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.GoodsAttribute;
import com.seamwhole.webtradeadmin.service.GoodsAttributeService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GoodsAttributeServiceHystrix implements GoodsAttributeService {
    @Override
    public PagesInfo<GoodsAttribute> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public GoodsAttribute queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(GoodsAttribute goodsAttribute) {

    }

    @Override
    public void update(GoodsAttribute goodsAttribute) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }
}
