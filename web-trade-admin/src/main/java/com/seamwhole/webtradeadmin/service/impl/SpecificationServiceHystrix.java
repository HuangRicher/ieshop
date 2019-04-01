package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Specification;
import com.seamwhole.webtradeadmin.service.SpecificationService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SpecificationServiceHystrix implements SpecificationService {
    @Override
    public PagesInfo<Specification> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public Specification queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(Specification specification) {

    }

    @Override
    public void update(Specification specification) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<Specification> queryList(Map<String, Object> params) {
        return null;
    }
}
