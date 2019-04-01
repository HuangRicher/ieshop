package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ProductMapper;
import com.seamwhole.servicetradecore.mapper.ext.ProductExtMapper;
import com.seamwhole.servicetradecore.mapper.model.ProductDO;
import com.seamwhole.servicetradecore.model.Product;
import com.seamwhole.servicetradecore.model.ProductExample;
import com.seamwhole.servicetradecore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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


    public List<ProductDO> queryList(Map<String, Object> map) {
        return productExtMapper.queryList(map);
    }



    public void save(Product goods) {
        productMapper.insertSelective(goods);
    }


    public void update(Product goods) {
        productMapper.updateByPrimaryKeySelective(goods);
    }


    public void delete(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ProductDO> queryShopProductList(Map<String, Object> params) {
        return productExtMapper.queryShopProductList(params);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        ProductExample example=new ProductExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        productMapper.deleteByExample(example);
    }

    @Override
    public void updateById(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product getById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<ProductDO> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<ProductDO> page= PageHelper.startPage(pageNum,pageSize);
        productExtMapper.queryShopProductList(params);
        return page.toPageInfo();
    }
}
