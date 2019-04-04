package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.AttributeCategory;
import com.seamwhole.webtradeadmin.service.AttributeCategoryService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class AttributeCategoryServiceHystrix implements AttributeCategoryService {
    @Override
    public PagesInfo<AttributeCategory> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public AttributeCategory queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(AttributeCategory attributeCategory) {

    }

    @Override
    public void update(AttributeCategory attributeCategory) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<AttributeCategory> queryList(Map<String, Object> params) {
        return null;
    }
}
