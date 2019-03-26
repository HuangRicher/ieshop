package com.seamwhole.servicetradecore.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.SysMacroMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysMacroExtMapper;
import com.seamwhole.servicetradecore.model.SysMacro;
import com.seamwhole.servicetradecore.model.SysMacroExample;
import com.seamwhole.servicetradecore.service.SysMacroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通用字典表Service实现类
 */
@Service("sysMacroService")
public class SysMacroServiceImpl implements SysMacroService {
    @Autowired
    private SysMacroMapper sysMacroMapper;
    @Autowired
    private SysMacroExtMapper sysMacroExtMapper;



    @Override
    public SysMacro queryObject(Long macroId) {
        return sysMacroMapper.selectByPrimaryKey(macroId);
    }

    @Override
    public List<SysMacro> queryList(Map<String, Object> map) {
        SysMacroExample example=new SysMacroExample();
        if(map.get("type")!=null)
            example.createCriteria().andTypeEqualTo((int)map.get("type"));
        return sysMacroMapper.selectByExample(example);
    }

    @Override
    public PageInfo<SysMacro> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        SysMacroExample example=new SysMacroExample();
        if(map.get("type")!=null)
            example.createCriteria().andTypeEqualTo((int)map.get("type"));
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()->sysMacroMapper.selectByExample(example));

    }

    @Override
    public int save(SysMacro sysMacro) {
        sysMacro.setGmtCreate(new Date());
        sysMacroMapper.insertSelective(sysMacro);
        return 1;
    }

    @Override
    public int update(SysMacro sysMacro) {
        sysMacro.setGmtModified(new Date());
        sysMacroMapper.updateByPrimaryKeySelective(sysMacro);
        return 1;
    }

    @Override
    public int delete(Long macroId) {
        sysMacroMapper.deleteByPrimaryKey(macroId);
        return 1;
    }

    @Override
    public int deleteBatch(Long[] macroIds) {
        SysMacroExample example=new SysMacroExample();
        example.createCriteria().andIdIn(Arrays.asList(macroIds));
        sysMacroMapper.deleteByExample(example);
        return 1;
    }

    @Override
    public List<SysMacro> queryMacrosByValue(String value) {
        return sysMacroExtMapper.queryMacrosByValue(value);
    }

    @Override
    public List<SysMacro> queryAllParent(Map<String, Object> map) {
        //map.put("type", 0);
        SysMacroExample example=new SysMacroExample();
        example.createCriteria().andTypeEqualTo(0);
        return sysMacroMapper.selectByExample(example);
    }
}
