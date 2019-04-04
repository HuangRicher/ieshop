package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.mapper.ScheduleJobMapper;
import com.seamwhole.servicetradecore.model.ScheduleJob;
import com.seamwhole.servicetradecore.model.ScheduleJobExample;
import com.seamwhole.servicetradecore.service.ScheduleJobService;
import com.seamwhole.servicetradecore.util.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
	@Autowired
    private Scheduler scheduler;
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;
	
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<ScheduleJob> scheduleJobList = this.queryList(new HashMap<String, Object>());
		for(ScheduleJob scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}
	
	@Override
	public ScheduleJob queryObject(Long jobId) {
		return scheduleJobMapper.selectByPrimaryKey(jobId);
	}

	@Override
	public List<ScheduleJob> queryList(Map<String, Object> map) {
		ScheduleJobExample example=new ScheduleJobExample();
		if(map.get("beanName")!=null)
			example.createCriteria().andBeanNameLike("%"+ map.get("beanName")+"%");
		return scheduleJobMapper.selectByExample(example);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		ScheduleJobExample example=new ScheduleJobExample();
		if(map.get("beanName")!=null)
			example.createCriteria().andBeanNameEqualTo((String) map.get("beanName"));
		return scheduleJobMapper.countByExample(example);
	}

	@Override
	@Transactional
	public void save(ScheduleJob scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
		scheduleJobMapper.insertSelective(scheduleJob);
        
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	@Override
	@Transactional
	public void update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

		scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
    }

	@Override
	@Transactional
    public void deleteBatch(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
    	}
    	
    	//删除数据
		ScheduleJobExample example=new ScheduleJobExample();
		example.createCriteria().andJobIdIn(Arrays.asList(jobIds));
		scheduleJobMapper.deleteByExample(example);
	}

	@Override
    public int updateBatch(Long[] jobIds, int status){
		ScheduleJobExample example=new ScheduleJobExample();
		example.createCriteria().andJobIdIn(Arrays.asList(jobIds));
		ScheduleJob job=new ScheduleJob();
		job.setStatus(status);
    	return scheduleJobMapper.updateByExampleSelective(job,example);
    }
    
	@Override
	@Transactional
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.run(scheduler, queryObject(jobId));
    	}
    }

	@Override
	@Transactional
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}
        
    	updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }

	@Override
	public PageInfo<ScheduleJob> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
		ScheduleJobExample example=new ScheduleJobExample();
		if(params.get("beanName")!=null)
			example.createCriteria().andBeanNameLike("%"+ params.get("beanName")+"%");
		Page<ScheduleJob> page= PageHelper.startPage(pageNum,pageSize);
		scheduleJobMapper.selectByExample(example);
		return page.toPageInfo();
	}
}
