package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ScheduleJobLog;
import com.seamwhole.servicetradecore.service.ScheduleJobLogService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/scheduleJobLog")
public class ScheduleJobLogResource {

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    @PostMapping("/queryByPage")
    public PagesInfo<ScheduleJobLog> queryByPage(@RequestBody Map<String, Object> params) {
        PageInfo<ScheduleJobLog> pageInfo=scheduleJobLogService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ScheduleJobLog>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{logId}")
    public ScheduleJobLog queryObject(@PathVariable("logId") Long logId) {
        return scheduleJobLogService.queryObject(logId);
    }
}
