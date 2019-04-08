package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysSmsLog;
import com.seamwhole.webtradeadmin.info.SysSmsLogDO;
import com.seamwhole.webtradeadmin.info.SysSmsLogWithBLOBs;
import com.seamwhole.webtradeadmin.service.SysSmsLogService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SysSmsLogServiceHystrix implements SysSmsLogService {
    @Override
    public PagesInfo<SysSmsLogDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public SysSmsLog queryObject(String id) {
        return null;
    }

    @Override
    public List<SysSmsLogDO> queryList(Map<String, Object> params) {
        return null;
    }

    @Override
    public SysSmsLogWithBLOBs sendSms(SysSmsLogWithBLOBs smsLog, Long userId) {
        return null;
    }
}
