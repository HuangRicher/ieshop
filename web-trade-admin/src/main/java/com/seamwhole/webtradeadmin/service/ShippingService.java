package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopShipping;
import com.seamwhole.webtradeadmin.service.impl.ShippingServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = ShippingServiceHystrix.class)
public interface ShippingService {

    @PostMapping("/shipping/queryByPage")
    PagesInfo<ShopShipping> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/shipping/queryObject/{id}")
    ShopShipping queryObject(@PathVariable("id") Integer id);

    @PostMapping("/shipping/save")
    void save(@RequestBody ShopShipping shipping);

    @PostMapping("/shipping/update")
    void update(@RequestBody ShopShipping shipping);

    @PostMapping("/shipping/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/shipping/queryList")
    List<ShopShipping> queryList(@RequestBody Map<String, Object> params);
}
