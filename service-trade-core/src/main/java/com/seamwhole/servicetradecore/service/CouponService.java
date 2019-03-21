package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.Coupon;

import java.util.List;
import java.util.Map;


public interface CouponService {

    Coupon queryObject(Integer couponId);

    List<Coupon> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Coupon userVo);

    void update(Coupon user);

    void delete(Long userId);

    void deleteBatch(Long[] userIds);

    List<Coupon> queryUserCoupons(Map<String, Object> map);

    Coupon queryMaxUserEnableCoupon(Map<String, Object> map);

    List<Coupon> queryUserCouponList(Map<String, Object> map);
}
