package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopBrand;
import com.seamwhole.webtradeadmin.service.BrandService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BrandServiceHystrix implements BrandService {
    @Override
    public PagesInfo<ShopBrand> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopBrand queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopBrand brand) {

    }

    @Override
    public void update(ShopBrand brand) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<ShopBrand> queryList(Map<String, Object> params) {
        return null;
    }
}
