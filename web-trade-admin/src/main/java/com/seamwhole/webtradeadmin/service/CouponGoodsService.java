package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.CouponGoods;
import com.seamwhole.webtradeadmin.service.impl.CouponGoodsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = CouponGoodsServiceHystrix.class)
public interface CouponGoodsService {

    @PostMapping("/couponGoods/queryByPage")
    PagesInfo<CouponGoods> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/couponGoods/queryObject/{id}")
    CouponGoods queryObject(@PathVariable("id") Integer id);

    @PostMapping("/couponGoods/save")
    void save(@RequestBody CouponGoods couponGoods);

    @PostMapping("/couponGoods/update")
    void update(@RequestBody CouponGoods couponGoods);

    @PostMapping("/couponGoods/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/couponGoods/queryList")
    List<CouponGoods> queryList(@RequestBody Map<String, Object> params);
}
