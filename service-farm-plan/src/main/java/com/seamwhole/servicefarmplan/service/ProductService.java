package com.seamwhole.servicefarmplan.service;

import com.seamwhole.servicefarmplan.model.Product;

import java.util.List;

public interface ProductService {

    void add(String name);

    void delete(Integer id);

    void updateById(Integer id,String name);

    List<Product> findAll();
}
