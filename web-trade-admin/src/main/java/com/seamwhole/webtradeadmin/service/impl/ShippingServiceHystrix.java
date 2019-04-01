package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopShipping;
import com.seamwhole.webtradeadmin.service.ShippingService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ShippingServiceHystrix implements ShippingService {
    @Override
    public PagesInfo<ShopShipping> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopShipping queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopShipping shipping) {

    }

    @Override
    public void update(ShopShipping shipping) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<ShopShipping> queryList(Map<String, Object> params) {
        return null;
    }
}
