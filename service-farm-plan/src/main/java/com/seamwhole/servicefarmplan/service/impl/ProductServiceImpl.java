package com.seamwhole.servicefarmplan.service.impl;

import com.seamwhole.servicefarmplan.mapper.ProductMapper;
import com.seamwhole.servicefarmplan.model.Product;
import com.seamwhole.servicefarmplan.model.ProductExample;
import com.seamwhole.servicefarmplan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;



    @Override
    public void add(String name) {
        Product product=new Product();
        product.setName(name);
        productMapper.insertSelective(product);
    }

    @Override
    public void delete(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(Integer id, String name) {
        Product product=new Product();
        product.setId(id);
        product.setName(name);
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public List<Product> findAll() {
        ProductExample example=new ProductExample();
        return productMapper.selectByExample(example);
    }
}
