package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopAttribute;
import com.seamwhole.webtradeadmin.info.ShopAttributeDO;
import com.seamwhole.webtradeadmin.service.impl.AttributeServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = AttributeServiceHystrix.class)
public interface AttributeService {

    @PostMapping("/shopAttribute/queryByPage")
    PagesInfo<ShopAttributeDO> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/shopAttribute/queryObject/{id}")
    ShopAttribute queryObject(@PathVariable("id") Integer id);

    @PostMapping("/shopAttribute/save")
    void save(@RequestBody ShopAttribute attribute);

    @PostMapping("/shopAttribute/update")
    void update(@RequestBody ShopAttribute attribute);

    @PostMapping("/shopAttribute/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/shopAttribute/queryList")
    List<ShopAttributeDO> queryList(@RequestBody Map<String, Object> params);
}
