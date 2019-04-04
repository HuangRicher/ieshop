package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ScheduleJob;
import com.seamwhole.webtradeadmin.service.ScheduleJobService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ScheduleJobServiceHystrix implements ScheduleJobService {
    @Override
    public PagesInfo<ScheduleJob> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ScheduleJob queryObject(Long jobId) {
        return null;
    }

    @Override
    public void save(ScheduleJob scheduleJob) {

    }

    @Override
    public void update(ScheduleJob scheduleJob) {

    }

    @Override
    public void deleteBatch(Long[] jobIds) {

    }

    @Override
    public void run(Long[] jobIds) {

    }

    @Override
    public void pause(Long[] jobIds) {

    }

    @Override
    public void resume(Long[] jobIds) {

    }
}
