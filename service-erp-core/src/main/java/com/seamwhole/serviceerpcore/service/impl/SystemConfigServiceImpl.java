package com.seamwhole.serviceerpcore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.mapper.SystemConfigMapper;
import com.seamwhole.serviceerpcore.mapper.ext.SystemConfigExtMapper;
import com.seamwhole.serviceerpcore.model.SystemConfig;
import com.seamwhole.serviceerpcore.model.SystemConfigExample;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.service.SystemConfigService;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {
    private Logger logger = LoggerFactory.getLogger(SystemConfigServiceImpl.class);

    @Resource
    private SystemConfigMapper systemConfigMapper;

    @Resource
    private SystemConfigExtMapper systemConfigExtMapper;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private LogServiceImpl logService;

    public SystemConfig getSystemConfig(long id) {
        return systemConfigMapper.selectByPrimaryKey(id);
    }

    public List<SystemConfig> getSystemConfig() {
        SystemConfigExample example = new SystemConfigExample();
        example.createCriteria().andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        return systemConfigMapper.selectByExample(example);
    }
    public List<SystemConfig> select(String companyName, int offset, int rows) {
        return systemConfigExtMapper.selectByConditionSystemConfig(companyName, offset, rows);
    }

    public Long countSystemConfig(String companyName) {
        return systemConfigExtMapper.countsBySystemConfig(companyName);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertSystemConfig(String beanJson, HttpServletRequest request) {
        SystemConfig systemConfig = JSONObject.parseObject(beanJson, SystemConfig.class);
        return systemConfigMapper.insertSelective(systemConfig);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateSystemConfig(String beanJson, Long id) {
        SystemConfig systemConfig = JSONObject.parseObject(beanJson, SystemConfig.class);
        systemConfig.setId(id);
        return systemConfigMapper.updateByPrimaryKeySelective(systemConfig);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteSystemConfig(Long id) {
        return systemConfigMapper.deleteByPrimaryKey(id);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteSystemConfig(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        SystemConfigExample example = new SystemConfigExample();
        example.createCriteria().andIdIn(idList);
        return systemConfigMapper.deleteByExample(example);
    }

    public int checkIsNameExist(Long id, String name) {
        SystemConfigExample example = new SystemConfigExample();
        example.createCriteria().andIdNotEqualTo(id).andCompanyNameEqualTo(name).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<SystemConfig> list = systemConfigMapper.selectByExample(example);
        return list.size();
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteSystemConfigByIds(String ids) {
        logService.insertLog(BusinessConstants.LOG_INTERFACE_NAME_SYSTEM_CONFIG,
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_DELETE).append(ids).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User userInfo=userService.getCurrentUser();
        String [] idArray=ids.split(",");
        return systemConfigExtMapper.batchDeleteSystemConfigByIds(new Date(),userInfo==null?null:userInfo.getId(),idArray);
    }
}
