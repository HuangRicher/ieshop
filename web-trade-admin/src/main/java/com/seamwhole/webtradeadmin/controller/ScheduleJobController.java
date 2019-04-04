package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ScheduleJob;
import com.seamwhole.webtradeadmin.service.ScheduleJobService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<ScheduleJob> page=scheduleJobService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 定时任务信息
     */
    @RequestMapping("/info/{jobId}")
    @RequiresPermissions("sys:schedule:info")
    public ResponseObject info(@PathVariable("jobId") Long jobId) {
        ScheduleJob schedule = scheduleJobService.queryObject(jobId);

        return ResponseObject.ok().put("schedule", schedule);
    }

    /**
     * 保存定时任务
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:schedule:save")
    public ResponseObject save(@RequestBody ScheduleJob scheduleJob) {

        scheduleJobService.save(scheduleJob);

        return ResponseObject.ok();
    }

    /**
     * 修改定时任务
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:schedule:update")
    public ResponseObject update(@RequestBody ScheduleJob scheduleJob) {

        scheduleJobService.update(scheduleJob);

        return ResponseObject.ok();
    }

    /**
     * 删除定时任务
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:schedule:delete")
    public ResponseObject delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);

        return ResponseObject.ok();
    }

    /**
     * 立即执行任务
     */
    @RequestMapping("/run")
    @RequiresPermissions("sys:schedule:run")
    public ResponseObject run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);

        return ResponseObject.ok();
    }

    /**
     * 暂停定时任务
     */
    @RequestMapping("/pause")
    @RequiresPermissions("sys:schedule:pause")
    public ResponseObject pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);

        return ResponseObject.ok();
    }

    /**
     * 恢复定时任务
     */
    @RequestMapping("/resume")
    @RequiresPermissions("sys:schedule:resume")
    public ResponseObject resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);

        return ResponseObject.ok();
    }

}
