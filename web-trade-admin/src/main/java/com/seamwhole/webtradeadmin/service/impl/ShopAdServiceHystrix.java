package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopAd;
import com.seamwhole.webtradeadmin.info.ShopAdDO;
import com.seamwhole.webtradeadmin.service.ShopAdService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ShopAdServiceHystrix implements ShopAdService {
    @Override
    public PagesInfo<ShopAdDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopAd queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopAd ad) {

    }

    @Override
    public void update(ShopAd ad) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<ShopAdDO> queryList(Map<String, Object> params) {
        return null;
    }
}
