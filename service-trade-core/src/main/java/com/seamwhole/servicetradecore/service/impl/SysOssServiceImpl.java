package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.SysOssMapper;
import com.seamwhole.servicetradecore.model.SysOss;
import com.seamwhole.servicetradecore.model.SysOssExample;
import com.seamwhole.servicetradecore.service.SysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysOssServiceImpl implements SysOssService {
    @Autowired
    private SysOssMapper sysOssMapper;


    @Override
    public PageInfo<SysOss> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        SysOssExample example=new SysOssExample();
        example.setOrderByClause(" create_date desc");
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()->sysOssMapper.selectByExample(example));
    }

    @Override
    public void save(SysOss ossEntity) {
        sysOssMapper.insertSelective(ossEntity);
    }

    @Override
    public void removeByIds(List<Long> asList) {
        SysOssExample example=new SysOssExample();
        example.createCriteria().andIdIn(asList);
        sysOssMapper.deleteByExample(example);
    }
}
