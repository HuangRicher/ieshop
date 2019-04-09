package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysOss;
import com.seamwhole.webtradeadmin.service.SysOssService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SysOssServiceHystrix implements SysOssService {
    @Override
    public PagesInfo<SysOss> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public void removeByIds(List<Long> longs) {

    }

    @Override
    public void save(SysOss ossEntity) {

    }
}
