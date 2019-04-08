package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SysConfig;
import com.seamwhole.webtradeadmin.service.impl.SysConfigServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysConfigServiceHystrix.class)
public interface SysConfigService {

    @PostMapping("/sysConfig/queryByPage")
    PagesInfo<SysConfig> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/sysConfig/queryByPage/{id}")
    SysConfig queryObject(@PathVariable("id") Long id);

    @PostMapping("/sysConfig/queryByPage")
    void save(@RequestBody SysConfig config);

    @PostMapping("/sysConfig/queryByPage")
    void update(@RequestBody SysConfig config);

    @PostMapping("/sysConfig/queryByPage")
    void deleteBatch(@RequestBody Long[] ids);

    <T> T getConfigObject(String key, Class<T> clazz);

    void updateValueByKey(String key, String s);
}
