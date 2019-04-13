package com.seamwhole.serviceerpcore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.mapper.LogMapper;
import com.seamwhole.serviceerpcore.mapper.ext.LogExtMapper;
import com.seamwhole.serviceerpcore.mapper.vo.LogVo4List;
import com.seamwhole.serviceerpcore.model.Log;
import com.seamwhole.serviceerpcore.model.LogExample;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.service.LogService;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.seamwhole.serviceerpcore.utils.Tools.getLocalIp;

@Service
public class LogServiceImpl implements LogService {
    private Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Resource
    private LogMapper logMapper;

    @Resource
    private LogExtMapper logExtMapper;

    public Log getLog(long id) {
        return logMapper.selectByPrimaryKey(id);
    }

    public List<Log> getLog() {
        LogExample example = new LogExample();
        return logMapper.selectByExample(example);
    }

    public List<LogVo4List> select(String operation, Integer usernameID, String clientIp, Integer status, String beginTime, String endTime,
                                   String contentdetails, int offset, int rows) {
        return logExtMapper.selectByConditionLog(operation, usernameID, clientIp, status, beginTime, endTime,
                            contentdetails, offset, rows);
    }

    public Long countLog(String operation, Integer usernameID, String clientIp, Integer status, String beginTime, String endTime,
                        String contentdetails) {
        return logExtMapper.countsByLog(operation, usernameID, clientIp, status, beginTime, endTime, contentdetails);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertLog(String beanJson, HttpServletRequest request) {
        Log log = JSONObject.parseObject(beanJson, Log.class);
        return logMapper.insertSelective(log);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateLog(String beanJson, Long id) {
        Log log = JSONObject.parseObject(beanJson, Log.class);
        log.setId(id);
        return logMapper.updateByPrimaryKeySelective(log);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteLog(Long id) {
        return logMapper.deleteByPrimaryKey(id);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteLog(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        LogExample example = new LogExample();
        example.createCriteria().andIdIn(idList);
        return logMapper.deleteByExample(example);
    }

    /**
     * 获取用户id
     * @param request
     * @return
     */
    public Long getUserId(HttpServletRequest request) {
        Object userInfo = request.getSession().getAttribute("user");
        if(userInfo!=null) {
            User user = (User) userInfo;
            return user.getId();
        } else {
            return null;
        }
    }

    public String getModule(String apiName){
        String moduleName = null;
        switch (apiName) {
            case BusinessConstants.LOG_INTERFACE_NAME_USER:
                moduleName = BusinessConstants.LOG_MODULE_NAME_USER; break;
            case BusinessConstants.LOG_INTERFACE_NAME_ROLE:
                moduleName = BusinessConstants.LOG_MODULE_NAME_ROLE; break;
            case BusinessConstants.LOG_INTERFACE_NAME_APP:
                moduleName =BusinessConstants.LOG_MODULE_NAME_APP; break;
            case BusinessConstants.LOG_INTERFACE_NAME_DEPOT:
                moduleName = BusinessConstants.LOG_MODULE_NAME_DEPOT; break;
            case BusinessConstants.LOG_INTERFACE_NAME_FUNCTIONS:
                moduleName = BusinessConstants.LOG_MODULE_NAME_FUNCTIONS; break;
            case BusinessConstants.LOG_INTERFACE_NAME_IN_OUT_ITEM:
                moduleName = BusinessConstants.LOG_MODULE_NAME_IN_OUT_ITEM; break;
            case BusinessConstants.LOG_INTERFACE_NAME_UNIT:
                moduleName = BusinessConstants.LOG_MODULE_NAME_UNIT; break;
            case BusinessConstants.LOG_INTERFACE_NAME_PERSON:
                moduleName = BusinessConstants.LOG_MODULE_NAME_PERSON; break;
            case BusinessConstants.LOG_INTERFACE_NAME_USER_BUSINESS:
                moduleName = BusinessConstants.LOG_MODULE_NAME_USER_BUSINESS; break;
            case BusinessConstants.LOG_INTERFACE_NAME_SYSTEM_CONFIG:
                moduleName = BusinessConstants.LOG_MODULE_NAME_SYSTEM_CONFIG; break;
            case BusinessConstants.LOG_INTERFACE_NAME_MATERIAL_PROPERTY:
                moduleName = BusinessConstants.LOG_MODULE_NAME_MATERIAL_PROPERTY; break;
            case BusinessConstants.LOG_INTERFACE_NAME_ACCOUNT:
                moduleName = BusinessConstants.LOG_MODULE_NAME_ACCOUNT; break;
            case BusinessConstants.LOG_INTERFACE_NAME_SUPPLIER:
                moduleName = BusinessConstants.LOG_MODULE_NAME_SUPPLIER; break;
            case BusinessConstants.LOG_INTERFACE_NAME_MATERIAL_CATEGORY:
                moduleName = BusinessConstants.LOG_MODULE_NAME_MATERIAL_CATEGORY; break;
            case BusinessConstants.LOG_INTERFACE_NAME_MATERIAL:
                moduleName = BusinessConstants.LOG_MODULE_NAME_MATERIAL; break;
            case BusinessConstants.LOG_INTERFACE_NAME_DEPOT_HEAD:
                moduleName = BusinessConstants.LOG_MODULE_NAME_DEPOT_HEAD; break;
            case BusinessConstants.LOG_INTERFACE_NAME_DEPOT_ITEM:
                moduleName = BusinessConstants.LOG_MODULE_NAME_DEPOT_ITEM; break;
            case BusinessConstants.LOG_INTERFACE_NAME_ACCOUNT_HEAD:
                moduleName = BusinessConstants.LOG_MODULE_NAME_ACCOUNT_HEAD; break;
            case BusinessConstants.LOG_INTERFACE_NAME_ACCOUNT_ITEM:
                moduleName = BusinessConstants.LOG_MODULE_NAME_ACCOUNT_ITEM; break;
            case BusinessConstants.LOG_INTERFACE_NAME_SERIAL_NUMBER:
                moduleName = BusinessConstants.LOG_MODULE_NAME_SERIAL_NUMBER; break;
            case BusinessConstants.LOG_INTERFACE_NAME_ORGANIZATION:
                moduleName = BusinessConstants.LOG_MODULE_NAME_ORGANIZATION; break;
        }
        return moduleName;
    }

    public void insertLog(String apiName, String type, HttpServletRequest request){
        Log log = new Log();
        log.setUserid(getUserId(request));
        log.setOperation(getModule(apiName));
        log.setClientip(getLocalIp(request));
        log.setCreatetime(new Date());
        Byte status = 0;
        log.setStatus(status);
        log.setContentdetails(type + getModule(apiName));
        log.setRemark(type + getModule(apiName));
        logMapper.insertSelective(log);
    }

}
