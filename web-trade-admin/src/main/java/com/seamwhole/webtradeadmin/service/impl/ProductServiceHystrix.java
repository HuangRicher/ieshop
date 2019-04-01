package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Product;
import com.seamwhole.webtradeadmin.info.ProductDO;
import com.seamwhole.webtradeadmin.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductServiceHystrix implements ProductService {
    @Override
    public List<ProductDO> queryList(Map<String, Object> params) {
        return null;
    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void save(Product product) {

    }

    @Override
    public Product queryObject(Integer id) {
        return null;
    }

    @Override
    public PagesInfo<ProductDO> queryByPage(Map<String, Object> params) {
        return null;
    }
}
