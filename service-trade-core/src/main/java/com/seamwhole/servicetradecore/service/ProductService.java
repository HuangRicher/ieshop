package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.Product;

import java.util.List;
import java.util.Map;


public interface ProductService {

    Product queryObject(Integer id);

    List<Product> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Product goods);

    void update(Product goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
