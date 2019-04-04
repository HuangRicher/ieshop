package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ScheduleJobLog;
import com.seamwhole.webtradeadmin.service.impl.ScheduleJobLogServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = ScheduleJobLogServiceHystrix.class)
public interface ScheduleJobLogService {

    @PostMapping("/scheduleJobLog/queryByPage")
    PagesInfo<ScheduleJobLog> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/scheduleJobLog/queryObject/{logId}")
    ScheduleJobLog queryObject(@PathVariable("logId") Long logId);
}
