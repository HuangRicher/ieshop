package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.AttributeCategory;
import com.seamwhole.webtradeadmin.service.impl.AttributeCategoryServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = AttributeCategoryServiceHystrix.class)
public interface AttributeCategoryService {
    @PostMapping("/attributeCategory/queryByPage")
    PagesInfo<AttributeCategory> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/attributeCategory/queryObject/{id}")
    AttributeCategory queryObject(@PathVariable("id") Integer id);

    @PostMapping("/attributeCategory/save")
    void save(@RequestBody AttributeCategory attributeCategory);

    @PostMapping("/attributeCategory/update")
    void update(@RequestBody AttributeCategory attributeCategory);

    @PostMapping("/attributeCategory/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/attributeCategory/queryList")
    List<AttributeCategory> queryList(@RequestBody Map<String, Object> params);
}
