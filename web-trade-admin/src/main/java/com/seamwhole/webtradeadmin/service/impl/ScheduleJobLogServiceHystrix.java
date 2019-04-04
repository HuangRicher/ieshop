package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ScheduleJobLog;
import com.seamwhole.webtradeadmin.service.ScheduleJobLogService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ScheduleJobLogServiceHystrix implements ScheduleJobLogService {
    @Override
    public PagesInfo<ScheduleJobLog> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ScheduleJobLog queryObject(Long logId) {
        return null;
    }
}
