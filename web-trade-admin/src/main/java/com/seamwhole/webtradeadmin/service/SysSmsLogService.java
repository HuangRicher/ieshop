package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SysSmsLog;
import com.seamwhole.webtradeadmin.info.SysSmsLogDO;
import com.seamwhole.webtradeadmin.info.SysSmsLogWithBLOBs;
import com.seamwhole.webtradeadmin.service.impl.SysSmsLogServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysSmsLogServiceHystrix.class)
public interface SysSmsLogService {
    @PostMapping("/sysSmsLog/queryByPage")
    PagesInfo<SysSmsLogDO> queryByPage(@RequestBody Map<String, Object> params);


    @GetMapping("/sysSmsLog/queryObject/{id}")
    SysSmsLog queryObject(@PathVariable("id") String id);


    @PostMapping("/sysSmsLog/queryList")
    List<SysSmsLogDO> queryList(@RequestBody Map<String, Object> params);


    @PostMapping("/sysSmsLog/sendSms/{userId}")
    SysSmsLogWithBLOBs sendSms(@RequestBody SysSmsLogWithBLOBs smsLog, @PathVariable("userId") Long userId);
}
