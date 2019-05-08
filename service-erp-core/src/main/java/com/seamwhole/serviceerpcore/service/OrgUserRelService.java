package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.OrgUserRel;

import javax.servlet.http.HttpServletRequest;


public interface OrgUserRelService {

    int insertOrgUserRel(String beanJson, HttpServletRequest request)throws Exception;
    
    int updateOrgUserRel(String beanJson, Long id)throws Exception ;
    
    int deleteOrgUserRel(Long id)throws Exception;
    
    int batchDeleteOrgUserRel(String ids)throws Exception;
    
    /**
     *  新增机构用户关联关系,反显id
     */
    OrgUserRel addOrgUserRel(OrgUserRel orgaUserRel) throws Exception;
    
    /**
     *  更新机构用户关联关系
     */
    OrgUserRel updateOrgUserRel(OrgUserRel orgaUserRel)throws Exception;
}
