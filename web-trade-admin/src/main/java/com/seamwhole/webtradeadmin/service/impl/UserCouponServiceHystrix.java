package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.UserCoupon;
import com.seamwhole.webtradeadmin.info.UserCouponDO;
import com.seamwhole.webtradeadmin.service.UserCouponService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserCouponServiceHystrix implements UserCouponService {
    @Override
    public PagesInfo<UserCouponDO> queryShopUserCouponByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public UserCoupon queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(UserCoupon userCoupon) {

    }

    @Override
    public void update(UserCoupon userCoupon) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<UserCouponDO> queryShopUserCouponList(Map<String, Object> params) {
        return null;
    }
}
