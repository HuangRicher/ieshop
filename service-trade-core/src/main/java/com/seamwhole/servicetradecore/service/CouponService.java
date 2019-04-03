package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.CouponDO;
import com.seamwhole.servicetradecore.model.Coupon;
import com.seamwhole.servicetradecore.util.ResponseObject;

import java.util.List;
import java.util.Map;


public interface CouponService {

    Coupon queryObject(Integer couponId);

    List<CouponDO> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Coupon userVo);

    void update(Coupon user);

    void delete(Integer userId);

    void deleteBatch(Integer[] userIds);

    List<CouponDO> queryUserCoupons(Map<String, Object> map);

    CouponDO queryMaxUserEnableCoupon(Map<String, Object> map);

    List<CouponDO> queryUserCouponList(Map<String, Object> map);

    CouponDO getUserCoupon(Integer id);

    void updateUserCoupon(CouponDO couponVo);

    ResponseObject publish(Map<String, Object> params);

    List<Coupon> queryShopList(Map<String, Object> params);

    PageInfo<Coupon> queryShopCommentByPage(Map<String, Object> params, Integer page, Integer limit);
}
