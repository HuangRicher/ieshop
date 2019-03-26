package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.SysOss;

import java.util.List;
import java.util.Map;

/**
 * 文件上传Service
 */
public interface SysOssService {

    /**
     * queryPage
     *
     * @param params
     * @return
     */
    PageInfo<SysOss> queryPage(Map<String, Object> params,Integer pageNum,Integer pageSize);

    void save(SysOss ossEntity);

    void removeByIds(List<Long> asList);
}
