package com.seamwhole.servicetradecore.service.impl;

import com.alibaba.fastjson.JSON;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.mapper.SysConfigMapper;
import com.seamwhole.servicetradecore.model.SysConfig;
import com.seamwhole.servicetradecore.model.SysConfigExample;
import com.seamwhole.servicetradecore.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		example.createCriteria().andKeyEqualTo(key);SysConfigExample example=new SysConfigExample();
		example.createCriteria().andKeyEqualTo(key);
		sysConfigDao.deleteBatch(ids);
	}

	@Override
	public List<SysConfig> queryList(Map<String, Object> map) {
		return sysConfigDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysConfigDao.queryTotal(map);
	}

	@Override
	public SysConfig queryObject(Long id) {
		return sysConfigDao.queryObject(id);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String value = sysConfigDao.queryByKey(key);
		if(StringUtils.isBlank(value)){
			return defaultValue;
		}
		return value;
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
