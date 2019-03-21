package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.UserCoupon;

import java.util.List;
import java.util.Map;


public interface UserCouponService {

    UserCoupon queryObject(Integer id);

    UserCoupon queryByCouponNumber(String couponNumber);

    List<UserCoupon> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserCoupon goods);

    void update(UserCoupon goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
