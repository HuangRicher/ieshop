package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopUser;
import com.seamwhole.webtradeadmin.info.ShopUserDO;
import com.seamwhole.webtradeadmin.service.ShopUserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ShopUserServiceHystrix implements ShopUserService{
    @Override
    public PagesInfo<ShopUserDO> queryShopUseByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopUser getShopUserById(Integer id) {
        return null;
    }

    @Override
    public void saveShopUser(ShopUser user) {

    }

    @Override
    public void updateShopUserById(ShopUser user) {

    }

    @Override
    public void deleteShopUserBatch(Integer[] ids) {

    }

    @Override
    public List<ShopUserDO> queryShopUserList(Map<String, Object> params) {
        return null;
    }

    @Override
    public int queryShopUserTotal(Map<String, Object> params) {
        return 0;
    }
}
