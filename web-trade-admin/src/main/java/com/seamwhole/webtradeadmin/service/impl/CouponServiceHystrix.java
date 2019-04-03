package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Coupon;
import com.seamwhole.webtradeadmin.service.CouponService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class CouponServiceHystrix implements CouponService {
    @Override
    public ResponseObject publish(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<Coupon> queryList(Map<String, Object> params) {
        return null;
    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public void update(Coupon coupon) {

    }

    @Override
    public void save(Coupon coupon) {

    }

    @Override
    public Coupon queryObject(Integer id) {
        return null;
    }

    @Override
    public PagesInfo<Coupon> queryByPage(Map<String, Object> params) {
        return null;
    }
}
