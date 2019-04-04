package com.seamwhole.servicetradecore.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ScheduleJobLogMapper;
import com.seamwhole.servicetradecore.model.ScheduleJobLog;
import com.seamwhole.servicetradecore.model.ScheduleJobLogExample;
import com.seamwhole.servicetradecore.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
	@Autowired
	private ScheduleJobLogMapper scheduleJobLogMapper;
	
	@Override
	public ScheduleJobLog queryObject(Long jobId) {
		return scheduleJobLogMapper.selectByPrimaryKey(jobId);
	}

	@Override
	public List<ScheduleJobLog> queryList(Map<String, Object> map) {
		ScheduleJobLogExample example=new ScheduleJobLogExample();
		if(map.get("jobId")!=null)
			example.createCriteria().andJobIdEqualTo((long)map.get("jobId"));
		return scheduleJobLogMapper.selectByExample(example);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		ScheduleJobLogExample example=new ScheduleJobLogExample();
		if(map.get("jobId")!=null)
			example.createCriteria().andJobIdEqualTo((long)map.get("jobId"));
		return scheduleJobLogMapper.countByExample(example);
	}

	@Override
	public void save(ScheduleJobLog log) {
		scheduleJobLogMapper.insertSelective(log);
	}

	@Override
	public PageInfo<ScheduleJobLog> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
		ScheduleJobLogExample example=new ScheduleJobLogExample();
		if(params.get("jobId")!=null)
			example.createCriteria().andJobIdEqualTo((long)params.get("jobId"));
		Page<ScheduleJobLog> page= PageHelper.startPage(pageNum,pageSize);
		scheduleJobLogMapper.selectByExample(example);
		return page.toPageInfo();
	}
}
