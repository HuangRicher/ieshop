package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.AdPosition;
import com.seamwhole.webtradeadmin.service.AdPositionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AdPositionServiceHystrix implements AdPositionService {
    @Override
    public PagesInfo<AdPosition> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public AdPosition queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(AdPosition adPosition) {

    }

    @Override
    public void update(AdPosition adPosition) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<AdPosition> queryList(Map<String, Object> params) {
        return null;
    }
}
