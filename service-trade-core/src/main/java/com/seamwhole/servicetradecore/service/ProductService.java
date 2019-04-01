package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ProductDO;
import com.seamwhole.servicetradecore.model.Product;

import java.util.List;
import java.util.Map;


public interface ProductService {

    ProductDO queryObject(Integer id);

    List<ProductDO> queryList(Map<String, Object> map);


    void save(Product goods);

    void update(Product goods);

    void delete(Integer id);


    List<ProductDO> queryShopProductList(Map<String, Object> params);

    void deleteBatch(Integer[] ids);

    void updateById(Product product);

    Product getById(Integer id);

    PageInfo<ProductDO> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);
}
