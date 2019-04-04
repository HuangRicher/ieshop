package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.UserCoupon;
import com.seamwhole.webtradeadmin.info.UserCouponDO;
import com.seamwhole.webtradeadmin.service.impl.UserCouponServiceHystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = UserCouponServiceHystrix.class)
public interface UserCouponService {


    @PostMapping("/shopUserCoupon/queryShopUserCouponByPage")
    PagesInfo<UserCouponDO> queryShopUserCouponByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/shopUserCoupon/queryObject/{id}")
    UserCoupon queryObject(@PathVariable("id") Integer id);

    @PostMapping("/shopUserCoupon/save")
    void save(@RequestBody UserCoupon userCoupon);

    @PostMapping("/shopUserCoupon/update")
    void update(@RequestBody UserCoupon userCoupon);

    @PostMapping("/shopUserCoupon/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/shopUserCoupon/queryShopUserCouponList")
    List<UserCouponDO> queryShopUserCouponList(@RequestBody Map<String, Object> params);
}
