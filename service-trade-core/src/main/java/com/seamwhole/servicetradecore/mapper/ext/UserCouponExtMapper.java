package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.model.UserCoupon;

import java.util.List;
import java.util.Map;

public interface UserCouponExtMapper {

    List<UserCoupon> queryList(Map<String, Object> map);
}
