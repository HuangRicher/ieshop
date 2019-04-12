package com.seamwhole.serviceerpcore.service;

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

public interface OrgUserRelService {

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertOrgaUserRel(String beanJson, HttpServletRequest request) {
        OrgUserRel orgaUserRel = JSONObject.parseObject(beanJson, OrgUserRel.class);
        return orgUserRelMapper.insertSelective(orgaUserRel);
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateOrgaUserRel(String beanJson, Long id) {
        OrgUserRel orgaUserRel = JSONObject.parseObject(beanJson, OrgUserRel.class);
        orgaUserRel.setId(id);
        return orgUserRelMapper.updateByPrimaryKeySelective(orgaUserRel);
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteOrgaUserRel(Long id) {
        return orgUserRelMapper.deleteByPrimaryKey(id);
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteOrgaUserRel(String ids) {
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
     * @Param: orgaUserRel
     * @return void
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public OrgUserRel addOrgaUserRel(OrgUserRel orgaUserRel) throws Exception{
        Date date = new Date();
        User userInfo=userService.getCurrentUser();
        //创建时间
        if(orgaUserRel.getCreateTime()==null){
            orgaUserRel.setCreateTime(date);
        }
        //创建人
        if(orgaUserRel.getCreator()==null){
            orgaUserRel.setCreator(userInfo==null?null:userInfo.getId());
        }
        //更新时间
        if(orgaUserRel.getUpdateTime()==null){
            orgaUserRel.setUpdateTime(date);
        }
        //更新人
        if(orgaUserRel.getUpdater()==null){
            orgaUserRel.setUpdater(userInfo==null?null:userInfo.getId());
        }
        orgaUserRel.setDeleteFlag(BusinessConstants.DELETE_FLAG_EXISTS);
        int i=orgUserRelExtMapper.addOrgaUserRel(orgaUserRel);
        if(i>0){
            return orgaUserRel;
        }
        return null;
    }
    /**
     * create by: cjl
     * description:
     *  更新机构用户关联关系
     * create time: 2019/3/12 9:40
     * @Param: orgaUserRel
     * @return void
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public OrgUserRel updateOrgaUserRel(OrgUserRel orgaUserRel) {
        User userInfo=userService.getCurrentUser();
        //更新时间
        if(orgaUserRel.getUpdateTime()==null){
            orgaUserRel.setUpdateTime(new Date());
        }
        //更新人
        if(orgaUserRel.getUpdater()==null){
            orgaUserRel.setUpdater(userInfo==null?null:userInfo.getId());
        }
       int i= orgUserRelExtMapper.updateOrgaUserRel(orgaUserRel);
        if(i>0){
            return orgaUserRel;
        }
        return null;
    }
}
