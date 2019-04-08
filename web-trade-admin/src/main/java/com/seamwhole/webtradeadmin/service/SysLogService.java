package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SysLog;
import com.seamwhole.webtradeadmin.service.impl.SysLogServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysLogServiceHystrix.class)
public interface SysLogService {


    @PostMapping("/sysLog/queryByPage")
    PagesInfo<SysLog> queryByPage(@RequestBody Map<String, Object> params);
}
