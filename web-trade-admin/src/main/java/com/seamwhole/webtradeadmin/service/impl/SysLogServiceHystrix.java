package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysLog;
import com.seamwhole.webtradeadmin.service.SysLogService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SysLogServiceHystrix implements SysLogService {
    @Override
    public PagesInfo<SysLog> queryByPage(Map<String, Object> params) {
        return null;
    }
}
