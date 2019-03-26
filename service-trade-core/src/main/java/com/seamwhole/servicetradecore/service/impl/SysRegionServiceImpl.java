package com.seamwhole.servicetradecore.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.SysRegionMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysRegionExtMapper;
import com.seamwhole.servicetradecore.mapper.model.SysRegionDO;
import com.seamwhole.servicetradecore.model.SysRegion;
import com.seamwhole.servicetradecore.model.SysRegionExample;
import com.seamwhole.servicetradecore.service.SysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 */
@Service("regionService")
public class SysRegionServiceImpl implements SysRegionService {
    @Autowired
    private SysRegionMapper sysRegionMapper;
    @Autowired
    private SysRegionExtMapper sysRegionExtMapper;



    @Override
    public SysRegion queryObject(Integer id) {
        return sysRegionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRegionDO> queryList(Map<String, Object> map) {
        return sysRegionExtMapper.queryList(map);
    }

    @Override
    public PageInfo<SysRegionDO> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        Page<SysRegionDO> page= PageHelper.startPage(pageNum,pageSize);
        sysRegionExtMapper.queryList(map);
        return page.toPageInfo();
    }

    @Override
    public int save(SysRegion region) {
        return sysRegionMapper.insertSelective(region);
    }

    @Override
    public int update(SysRegion region) {
        return sysRegionMapper.updateByPrimaryKeySelective(region);
    }

    @Override
    public int delete(Integer id) {
        return sysRegionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        SysRegionExample example=new SysRegionExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        return sysRegionMapper.deleteByExample(example);
    }
}
