package com.seamwhole.servicetradecore.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.SysLogMapper;
import com.seamwhole.servicetradecore.model.SysLog;
import com.seamwhole.servicetradecore.model.SysLogExample;
import com.seamwhole.servicetradecore.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public PageInfo<SysLog> queryPage(Map<String, Object> params,Integer pageNum,Integer pageSize) {
        //排序
        SysLogExample example=new SysLogExample();
        example.setOrderByClause(" create_date desc");
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()->sysLogMapper.selectByExample(example));
    }
}
