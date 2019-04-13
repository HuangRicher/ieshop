package com.seamwhole.serviceerpcore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.mapper.FunctionsMapper;
import com.seamwhole.serviceerpcore.mapper.ext.FunctionsExtMapper;
import com.seamwhole.serviceerpcore.model.Functions;
import com.seamwhole.serviceerpcore.model.FunctionsExample;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.service.FunctionsService;
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
public class FunctionsServiceImpl implements FunctionsService {
    private Logger logger = LoggerFactory.getLogger(FunctionsServiceImpl.class);

    @Resource
    private FunctionsMapper functionsMapper;

    @Resource
    private FunctionsExtMapper functionsExtMapper;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private LogServiceImpl logService;

    public Functions getFunctions(long id) {
        return functionsMapper.selectByPrimaryKey(id);
    }

    public List<Functions> getFunctions() {
        FunctionsExample example = new FunctionsExample();
        return functionsMapper.selectByExample(example);
    }

    public List<Functions> select(String name, String type, int offset, int rows) {
        return functionsExtMapper.selectByConditionFunctions(name, type, offset, rows);
    }

    public Long countFunctions(String name, String type) {
        return functionsExtMapper.countsByFunctions(name, type);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertFunctions(String beanJson, HttpServletRequest request) {
        Functions depot = JSONObject.parseObject(beanJson, Functions.class);
        return functionsMapper.insertSelective(depot);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateFunctions(String beanJson, Long id) {
        Functions depot = JSONObject.parseObject(beanJson, Functions.class);
        depot.setId(id);
        return functionsMapper.updateByPrimaryKeySelective(depot);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteFunctions(Long id) {
        return functionsMapper.deleteByPrimaryKey(id);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteFunctions(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        FunctionsExample example = new FunctionsExample();
        example.createCriteria().andIdIn(idList);
        return functionsMapper.deleteByExample(example);
    }

    public int checkIsNameExist(Long id, String name) {
        FunctionsExample example = new FunctionsExample();
        example.createCriteria().andIdNotEqualTo(id).andNameEqualTo(name).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<Functions> list = functionsMapper.selectByExample(example);
        return list.size();
    }

    public List<Functions> getRoleFunctions(String pNumber) {
        FunctionsExample example = new FunctionsExample();
        example.createCriteria().andEnabledEqualTo(true).andPnumberEqualTo(pNumber);
        example.setOrderByClause("Sort");
        List<Functions> list = functionsMapper.selectByExample(example);
        return list;
    }

    public List<Functions> findRoleFunctions(String pnumber){
        FunctionsExample example = new FunctionsExample();
        example.createCriteria().andEnabledEqualTo(true).andPnumberEqualTo(pnumber);
        example.setOrderByClause("Sort");
        List<Functions> list = functionsMapper.selectByExample(example);
        return list;
    }

    public List<Functions> findByIds(String functionsIds){
        List<Long> idList = StringUtil.strToLongList(functionsIds);
        FunctionsExample example = new FunctionsExample();
        example.createCriteria().andEnabledEqualTo(true).andIdIn(idList);
        example.setOrderByClause("Sort asc");
        List<Functions> list = functionsMapper.selectByExample(example);
        return list;
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteFunctionsByIds(String ids) {
        logService.insertLog(BusinessConstants.LOG_INTERFACE_NAME_FUNCTIONS,
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_DELETE).append(ids).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User userInfo=userService.getCurrentUser();
        String [] idArray=ids.split(",");
        return functionsExtMapper.batchDeleteFunctionsByIds(new Date(),userInfo==null?null:userInfo.getId(),idArray);
    }
}
