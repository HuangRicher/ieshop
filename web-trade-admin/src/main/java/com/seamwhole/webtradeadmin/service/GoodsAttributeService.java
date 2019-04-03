package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.GoodsAttribute;
import com.seamwhole.webtradeadmin.service.impl.GoodsAttributeServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = GoodsAttributeServiceHystrix.class)
public interface GoodsAttributeService {

    @PostMapping("/goodsAttribute/queryByPage")
    PagesInfo<GoodsAttribute> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/goodsAttribute/queryObject/{id}")
    GoodsAttribute queryObject(@PathVariable("id") Integer id);

    @PostMapping("/goodsAttribute/save")
    void save(@RequestBody GoodsAttribute goodsAttribute);

    @PostMapping("/goodsAttribute/update")
    void update(@RequestBody GoodsAttribute goodsAttribute);

    @PostMapping("/goodsAttribute/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);
}
