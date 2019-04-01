package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopCart;
import com.seamwhole.webtradeadmin.info.ShopCartDO;
import com.seamwhole.webtradeadmin.service.CartService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CartServiceHystrix implements CartService {
    @Override
    public PagesInfo<ShopCartDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopCart queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopCart cart) {

    }

    @Override
    public void update(ShopCart cart) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }
}
