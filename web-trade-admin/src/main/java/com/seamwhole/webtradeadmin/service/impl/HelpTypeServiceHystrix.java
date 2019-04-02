package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.HelpType;
import com.seamwhole.webtradeadmin.service.HelpTypeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HelpTypeServiceHystrix implements HelpTypeService {
    @Override
    public PagesInfo<HelpType> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public HelpType queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(HelpType helpType) {

    }

    @Override
    public void update(HelpType helpType) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<HelpType> queryList(Map<String, Object> params) {
        return null;
    }
}
