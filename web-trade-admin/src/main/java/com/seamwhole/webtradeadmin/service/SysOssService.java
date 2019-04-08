package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysOss;

import java.util.List;
import java.util.Map;

public interface SysOssService {
    PagesInfo<SysOss> queryByPage(Map<String, Object> params);

    void removeByIds(List<Long> longs);

    void save(SysOss ossEntity);
}
