package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.AddressDO;
import com.seamwhole.webtradeadmin.info.ShopAddress;
import com.seamwhole.webtradeadmin.service.AddressService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AddressServiceHystrix implements AddressService {
    @Override
    public PagesInfo<AddressDO> queryShopUserAddressByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public AddressDO queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopAddress address) {

    }

    @Override
    public void update(ShopAddress address) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }
}
