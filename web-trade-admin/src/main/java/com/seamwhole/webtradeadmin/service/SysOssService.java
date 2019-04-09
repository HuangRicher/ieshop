package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SysOss;
import com.seamwhole.webtradeadmin.service.impl.SysOssServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysOssServiceHystrix.class)
public interface SysOssService {
    @PostMapping("/sysOss/queryByPage")
    PagesInfo<SysOss> queryByPage(@RequestBody Map<String, Object> params);

    @PostMapping("/sysOss/removeByIds")
    void removeByIds(@RequestBody List<Long> longs);

    @PostMapping("/sysOss/save")
    void save(@RequestBody SysOss ossEntity);
}
