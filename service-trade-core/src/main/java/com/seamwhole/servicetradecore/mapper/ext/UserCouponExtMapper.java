package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.UserCouponDO;
import com.seamwhole.servicetradecore.model.UserCoupon;

import java.util.List;
import java.util.Map;

public interface UserCouponExtMapper {

    List<UserCoupon> queryList(Map<String, Object> map);

    List<UserCouponDO> queryShopUserCouponList(Map<String, Object> map);
}
