package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.UserCoupon;
import com.seamwhole.webtradeadmin.info.UserCouponDO;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Map;
@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysUserMenuServiceHystrix.class)
public interface UserCouponService {
    PagesInfo<UserCouponDO> queryShopUserCouponByPage(Map<String, Object> params);

    UserCoupon queryObject(Integer id);

    void save(UserCoupon userCoupon);

    void update(UserCoupon userCoupon);

    void deleteBatch(Integer[] ids);

    List<UserCouponDO> queryShopUserCouponList(Map<String, Object> params);
}
