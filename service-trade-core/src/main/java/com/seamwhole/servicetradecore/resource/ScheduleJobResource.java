package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ScheduleJob;
import com.seamwhole.servicetradecore.service.ScheduleJobService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/scheduleJob")
public class ScheduleJobResource {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @PostMapping("/queryByPage")
    public PagesInfo<ScheduleJob> queryByPage(@RequestBody Map<String, Object> params) {
        PageInfo<ScheduleJob> pageInfo=scheduleJobService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ScheduleJob>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{jobId}")
    public ScheduleJob queryObject(@PathVariable("jobId") Long jobId) {
        return scheduleJobService.queryObject(jobId);
    }

    @PostMapping("/save")
    public void save(@RequestBody ScheduleJob scheduleJob) {
        scheduleJobService.save(scheduleJob);
    }

    @PostMapping("/update")
    public void update(@RequestBody ScheduleJob scheduleJob) {
        scheduleJobService.update(scheduleJob);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);
    }

    @PostMapping("/run")
    public void run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
    }

    @PostMapping("/pause")
    public void pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
    }

    @PostMapping("/resume")
    public void resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
    }
}
