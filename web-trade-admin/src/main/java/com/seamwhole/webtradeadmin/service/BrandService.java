package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopBrand;
import com.seamwhole.webtradeadmin.service.impl.BrandServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = BrandServiceHystrix.class)
public interface BrandService {
    @PostMapping("/brand/queryByPage")
    PagesInfo<ShopBrand> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/brand/queryObject/{id}")
    ShopBrand queryObject(@PathVariable("id") Integer id);

    @PostMapping("/brand/save")
    void save(@RequestBody ShopBrand brand);

    @PostMapping("/brand/update")
    void update(@RequestBody ShopBrand brand);

    @PostMapping("/brand/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/brand/queryList")
    List<ShopBrand> queryList(@RequestBody Map<String, Object> params);
}
