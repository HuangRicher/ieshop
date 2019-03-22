package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.mapper.model.CouponDO;
import com.seamwhole.servicetradecore.model.Coupon;

import java.util.List;
import java.util.Map;


public interface CouponService {

    Coupon queryObject(Integer couponId);

    List<CouponDO> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Coupon userVo);

    void update(Coupon user);

    void delete(Short userId);

    void deleteBatch(Short[] userIds);

    List<CouponDO> queryUserCoupons(Map<String, Object> map);

    CouponDO queryMaxUserEnableCoupon(Map<String, Object> map);

    List<CouponDO> queryUserCouponList(Map<String, Object> map);

    CouponDO getUserCoupon(Integer id);
}
