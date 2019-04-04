package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ScheduleJob;
import com.seamwhole.webtradeadmin.service.impl.ScheduleJobServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = ScheduleJobServiceHystrix.class)
public interface ScheduleJobService {
    @PostMapping("/scheduleJob/queryByPage")
    PagesInfo<ScheduleJob> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/scheduleJob/queryObject/{jobId}")
    ScheduleJob queryObject(@PathVariable("jobId") Long jobId);

    @PostMapping("/scheduleJob/save")
    void save(@RequestBody ScheduleJob scheduleJob);

    @PostMapping("/scheduleJob/update")
    void update(@RequestBody ScheduleJob scheduleJob);

    @PostMapping("/scheduleJob/deleteBatch")
    void deleteBatch(@RequestBody Long[] jobIds);

    @PostMapping("/scheduleJob/run")
    void run(@RequestBody Long[] jobIds);

    @PostMapping("/scheduleJob/pause")
    void pause(@RequestBody Long[] jobIds);

    @PostMapping("/scheduleJob/resume")
    void resume(@RequestBody Long[] jobIds);
}
