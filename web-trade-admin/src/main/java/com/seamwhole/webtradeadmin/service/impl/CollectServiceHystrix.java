package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopCollect;
import com.seamwhole.webtradeadmin.info.ShopCollectDO;
import com.seamwhole.webtradeadmin.service.CollectService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CollectServiceHystrix implements CollectService {
    @Override
    public PagesInfo<ShopCollectDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopCollect queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopCollect collect) {

    }

    @Override
    public void update(ShopCollect collect) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }
}
