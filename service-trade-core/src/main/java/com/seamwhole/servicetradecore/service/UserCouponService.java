package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.UserCoupon;

import java.util.List;
import java.util.Map;


public interface UserCouponService {

    List<UserCoupon> queryList(Map<String, Object> map);

    void save(UserCoupon goods);

    void update(UserCoupon goods);
}
