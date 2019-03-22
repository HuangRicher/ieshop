package com.seamwhole.servicetradecore.service.impl;

import com.platform.dao.ApiProductMapper;
import com.platform.entity.ProductVo;
import com.seamwhole.servicetradecore.mapper.ProductMapper;
import com.seamwhole.servicetradecore.mapper.ext.ProductExtMapper;
import com.seamwhole.servicetradecore.mapper.model.ProductDO;
import com.seamwhole.servicetradecore.model.Product;
import com.seamwhole.servicetradecore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductExtMapper productExtMapper;




    public ProductDO queryObject(Integer id) {
        return productExtMapper.queryObject(id);
    }


    public List<Product> queryList(Map<String, Object> map) {
        return productDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return productDao.queryTotal(map);
    }


    public void save(Product goods) {
        productDao.save(goods);
    }


    public void update(Product goods) {
        productDao.update(goods);
    }


    public void delete(Integer id) {
        productDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        productDao.deleteBatch(ids);
    }

}
