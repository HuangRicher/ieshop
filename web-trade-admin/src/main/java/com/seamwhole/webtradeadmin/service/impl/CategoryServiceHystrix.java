package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Category;
import com.seamwhole.webtradeadmin.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CategoryServiceHystrix implements CategoryService {
    @Override
    public PagesInfo<Category> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public Category queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<Category> queryList(Map<String, Object> params) {
        return null;
    }
}
