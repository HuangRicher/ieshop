package com.seamwhole.servicetradecore.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.mapper.SysConfigMapper;
import com.seamwhole.servicetradecore.model.SysConfig;
import com.seamwhole.servicetradecore.model.SysConfigExample;
import com.seamwhole.servicetradecore.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigMapper sysConfigMapper;
	
	@Override
	public void save(SysConfig config) {
		sysConfigMapper.insertSelective(config);
	}

	@Override
	public void update(SysConfig config) {
		sysConfigMapper.updateByPrimaryKeySelective(config);
	}

	@Override
	public void updateValueByKey(String key, String value) {
		SysConfigExample example=new SysConfigExample();
		example.createCriteria().andKeyEqualTo(key);
		SysConfig config=new SysConfig();
		config.setValue(value);
		sysConfigMapper.updateByExampleSelective(config,example);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		SysConfigExample example=new SysConfigExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		sysConfigMapper.deleteByExample(example);
	}

	@Override
	public List<SysConfig> queryList(Map<String, Object> map) {
		SysConfigExample example=new SysConfigExample();
		if(map.get("key")!=null)
			example.createCriteria().andStatusEqualTo(2).andKeyLike("%"+map.get("key")+"%");
		return sysConfigMapper.selectByExample(example);
	}

	@Override
	public PageInfo<SysConfig> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
		SysConfigExample example=new SysConfigExample();
		if(map.get("key")!=null)
			example.createCriteria().andStatusEqualTo(2).andKeyLike("%"+map.get("key")+"%");
		Page<SysConfig> page= PageHelper.startPage(pageNum,pageSize);
		sysConfigMapper.selectByExample(example);
		return page.toPageInfo();
	}

	@Override
	public SysConfig queryObject(Long id) {
		return sysConfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		SysConfigExample example=new SysConfigExample();
		example.createCriteria().andKeyEqualTo(key);
		List<SysConfig> list=sysConfigMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return defaultValue;
		}else {
			return list.get(0).getValue();
		}
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key, null);
		if(StringUtils.isNotBlank(value)){
			return JSON.parseObject(value, clazz);
		}
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new CheckException("获取参数失败");
		}
	}
}
