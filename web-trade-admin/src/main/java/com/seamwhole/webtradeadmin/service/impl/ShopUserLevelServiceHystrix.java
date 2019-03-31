package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.UserLevel;
import com.seamwhole.webtradeadmin.service.ShopUserLevelService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ShopUserLevelServiceHystrix implements ShopUserLevelService{
    @Override
    public List<UserLevel> queryShopUserLevelList(Map<String, Object> params) {
        return null;
    }

    @Override
    public void deleteShopUserLevelBatch(Integer[] ids) {

    }

    @Override
    public void updateShopUserLevelById(UserLevel userLevel) {

    }

    @Override
    public void saveShopUserLevel(UserLevel userLevel) {

    }

    @Override
    public UserLevel getShopUserLevelById(Integer id) {
        return null;
    }

    @Override
    public PagesInfo<UserLevel> queryShopUserLevel(Map<String, Object> params) {
        return null;
    }
}
