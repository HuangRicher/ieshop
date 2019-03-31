package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.AddressDO;
import com.seamwhole.webtradeadmin.info.ShopAddress;

import java.util.Map;

public interface AddressService {
    PagesInfo<AddressDO> queryShopUserAddressByPage(Map<String, Object> params);

    AddressDO queryObject(Integer id);

    void save(ShopAddress address);

    void update(ShopAddress address);

    void deleteBatch(Integer[] ids);
}
