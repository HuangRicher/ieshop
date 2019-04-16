package com.seamwhole.serviceerpcore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.mapper.PersonMapper;
import com.seamwhole.serviceerpcore.mapper.ext.AccountHeadExtMapper;
import com.seamwhole.serviceerpcore.mapper.ext.DepotHeadExtMapper;
import com.seamwhole.serviceerpcore.mapper.ext.PersonExtMapper;
import com.seamwhole.serviceerpcore.model.*;
import com.seamwhole.serviceerpcore.service.PersonService;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
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
public class PersonServiceImpl implements PersonService {
    private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Resource
    private PersonMapper personMapper;

    @Resource
    private PersonExtMapper personExtMapper;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private LogServiceImpl logService;
    @Resource
    private AccountHeadExtMapper accountHeadExtMapper;
    @Resource
    private DepotHeadExtMapper depotHeadExtMapper;

    public Person getPerson(long id) {
        return personMapper.selectByPrimaryKey(id);
    }

    public List<Person> getPerson() {
        PersonExample example = new PersonExample();
        example.createCriteria().andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        return personMapper.selectByExample(example);
    }

    public List<Person> select(String name, String type, int offset, int rows) {
        return personExtMapper.selectByConditionPerson(name, type, offset, rows);
    }

    public Long countPerson(String name, String type) {
        return personExtMapper.countsByPerson(name, type);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertPerson(String beanJson, HttpServletRequest request) {
        Person person = JSONObject.parseObject(beanJson, Person.class);
        return personMapper.insertSelective(person);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updatePerson(String beanJson, Long id) {
        Person person = JSONObject.parseObject(beanJson, Person.class);
        person.setId(id);
        return personMapper.updateByPrimaryKeySelective(person);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deletePerson(Long id) {
        return personMapper.deleteByPrimaryKey(id);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeletePerson(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        PersonExample example = new PersonExample();
        example.createCriteria().andIdIn(idList);
        return personMapper.deleteByExample(example);
    }

    public int checkIsNameExist(Long id, String name) {
        PersonExample example = new PersonExample();
        example.createCriteria().andIdNotEqualTo(id).andNameEqualTo(name).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<Person> list = personMapper.selectByExample(example);
        return list.size();
    }

    public String getPersonByIds(String personIDs) {
        List<Long> ids = StringUtil.strToLongList(personIDs);
        PersonExample example = new PersonExample();
        example.createCriteria().andIdIn(ids).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        example.setOrderByClause("Id asc");
        List<Person> list = personMapper.selectByExample(example);
        StringBuffer sb = new StringBuffer();
        if (null != list) {
            for (Person person : list) {
                sb.append(person.getName() + " ");
            }
        }
        return  sb.toString();
    }

    public List<Person> getPersonByType(String type) {
        PersonExample example = new PersonExample();
        example.createCriteria().andTypeEqualTo(type).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        example.setOrderByClause("Id asc");
        return personMapper.selectByExample(example);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeletePersonByIds(String ids) {
        logService.insertLog(BusinessConstants.LOG_INTERFACE_NAME_PERSON,
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_DELETE).append(ids).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User userInfo=userService.getCurrentUser();
        String [] idArray=ids.split(",");
        return personExtMapper.batchDeletePersonByIds(new Date(),userInfo==null?null:userInfo.getId(),idArray);
    }
    /**
     * create by: qiankunpingtai
     * website：https://qiankunpingtai.cn
     * description:
     *  正常删除，要考虑数据完整性，进行完整性校验
     * create time: 2019/4/10 15:14
     * @Param: ids
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeletePersonByIdsNormal(String ids) throws Exception {
        /**
         * 校验
         * 1、财务主表	jsh_accounthead
         * 2、单据主表	jsh_depothead
         * 是否有相关数据
         * */
        int deleteTotal=0;
        if(StringUtils.isEmpty(ids)){
            return deleteTotal;
        }
        String [] idArray=ids.split(",");
        /**
         * 校验财务主表	jsh_accounthead
         * */
        List<AccountHead> accountHeadList=accountHeadExtMapper.getAccountHeadListByHandsPersonIds(idArray);
        if(accountHeadList!=null&&accountHeadList.size()>0){
            logger.error("异常码[{}],异常提示[{}],参数,HandsPersonIds[{}]",
                    ExceptionConstants.DELETE_FORCE_CONFIRM_CODE,ExceptionConstants.DELETE_FORCE_CONFIRM_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.DELETE_FORCE_CONFIRM_CODE,
                    ExceptionConstants.DELETE_FORCE_CONFIRM_MSG);
        }
        /**
         * 校验单据主表	jsh_depothead
         * */
        List<DepotHead> depotHeadList=depotHeadExtMapper.getDepotHeadListByHandsPersonIds(idArray);
        if(depotHeadList!=null&&depotHeadList.size()>0){
            logger.error("异常码[{}],异常提示[{}],参数,HandsPersonIds[{}]",
                    ExceptionConstants.DELETE_FORCE_CONFIRM_CODE,ExceptionConstants.DELETE_FORCE_CONFIRM_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.DELETE_FORCE_CONFIRM_CODE,
                    ExceptionConstants.DELETE_FORCE_CONFIRM_MSG);
        }
        /**
         * 校验通过执行删除操作
         * */
        deleteTotal= batchDeletePersonByIds(ids);
        return deleteTotal;
    }
}
