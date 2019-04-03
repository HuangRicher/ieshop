package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.Coupon;
import com.seamwhole.webtradeadmin.service.impl.CouponServiceHystrix;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = CouponServiceHystrix.class)
public interface CouponService {


    @PostMapping("/coupon/publish")
    ResponseObject publish(@RequestBody Map<String, Object> params);

    @PostMapping("/coupon/queryList")
    List<Coupon> queryList(@RequestBody Map<String, Object> params);

    @PostMapping("/coupon/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/coupon/update")
    void update(@RequestBody Coupon coupon);

    @PostMapping("/coupon/save")
    void save(@RequestBody Coupon coupon);

    @GetMapping("/coupon/queryObject/{id}")
    Coupon queryObject(@PathVariable("id") Integer id);

    @PostMapping("/coupon/queryByPage")
    PagesInfo<Coupon> queryByPage(@RequestBody Map<String, Object> params);
}
