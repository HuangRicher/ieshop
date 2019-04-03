package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.FootPrint;
import com.seamwhole.webtradeadmin.info.ShopFootPrintDO;
import com.seamwhole.webtradeadmin.service.FootprintService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FootprintServiceHystrix implements FootprintService {
    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public void update(FootPrint footprint) {

    }

    @Override
    public void save(FootPrint footprint) {

    }

    @Override
    public FootPrint queryObject(Integer id) {
        return null;
    }

    @Override
    public PagesInfo<ShopFootPrintDO> queryByPage(Map<String, Object> params) {
        return null;
    }
}
