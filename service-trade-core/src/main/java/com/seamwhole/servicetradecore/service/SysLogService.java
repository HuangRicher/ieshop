package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.SysLog;

import java.util.Map;

/**
 * 系统日志
 */
public interface SysLogService {
    /**
     * 分页
     *
     * @param params
     * @return
     */
    PageInfo<SysLog> queryPage(Map<String, Object> params,Integer pageNum,Integer pageSize);
}
