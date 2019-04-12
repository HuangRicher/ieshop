package com.seamwhole.serviceerpcore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.mapper.OrgUserRelMapper;
import com.seamwhole.serviceerpcore.mapper.ext.OrgUserRelExtMapper;
import com.seamwhole.serviceerpcore.model.OrgUserRel;
import com.seamwhole.serviceerpcore.model.OrgUserRelExample;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @Author: cjl
 * @Date: 2019/3/11 18:11
 */
@Service
public class OrgUserRelService {
    private Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    @Resource
    private OrgUserRelMapper orgUserRelMapper;
    @Resource
    private OrgUserRelExtMapper orgUserRelExtMapper;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private LogService logService;
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertOrgUserRel(String beanJson, HttpServletRequest request) {
        OrgUserRel OrgUserRel = JSONObject.parseObject(beanJson, OrgUserRel.class);
        return orgUserRelMapper.insertSelective(OrgUserRel);
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateOrgUserRel(String beanJson, Long id) {
        OrgUserRel OrgUserRel = JSONObject.parseObject(beanJson, OrgUserRel.class);
        OrgUserRel.setId(id);
        return orgUserRelMapper.updateByPrimaryKeySelective(OrgUserRel);
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteOrgUserRel(Long id) {
        return orgUserRelMapper.deleteByPrimaryKey(id);
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteOrgUserRel(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        OrgUserRelExample example = new OrgUserRelExample();
        example.createCriteria().andIdIn(idList);
        return orgUserRelMapper.deleteByExample(example);
    }
    /**
     * create by: cjl
     * description:
     *  新增机构用户关联关系,反显id
     * create time: 2019/3/12 9:40
     * @Param: OrgUserRel
     * @return void
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public OrgUserRel addOrgUserRel(OrgUserRel OrgUserRel) throws Exception{
        Date date = new Date();
        User userInfo=userService.getCurrentUser();
        //创建时间
        if(OrgUserRel.getCreateTime()==null){
            OrgUserRel.setCreateTime(date);
        }
        //创建人
        if(OrgUserRel.getCreator()==null){
            OrgUserRel.setCreator(userInfo==null?null:userInfo.getId());
        }
        //更新时间
        if(OrgUserRel.getUpdateTime()==null){
            OrgUserRel.setUpdateTime(date);
        }
        //更新人
        if(OrgUserRel.getUpdater()==null){
            OrgUserRel.setUpdater(userInfo==null?null:userInfo.getId());
        }
        OrgUserRel.setDeleteFlag(BusinessConstants.DELETE_FLAG_EXISTS);
        int i=orgUserRelExtMapper.addOrgaUserRel(OrgUserRel);
        if(i>0){
            return OrgUserRel;
        }
        return null;
    }
    /**
     * create by: cjl
     * description:
     *  更新机构用户关联关系
     * create time: 2019/3/12 9:40
     * @Param: OrgUserRel
     * @return void
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public OrgUserRel updateOrgUserRel(OrgUserRel OrgUserRel) {
        User userInfo=userService.getCurrentUser();
        //更新时间
        if(OrgUserRel.getUpdateTime()==null){
            OrgUserRel.setUpdateTime(new Date());
        }
        //更新人
        if(OrgUserRel.getUpdater()==null){
            OrgUserRel.setUpdater(userInfo==null?null:userInfo.getId());
        }
       int i= orgUserRelExtMapper.updateOrgaUserRel(OrgUserRel);
        if(i>0){
            return OrgUserRel;
        }
        return null;
    }
}
