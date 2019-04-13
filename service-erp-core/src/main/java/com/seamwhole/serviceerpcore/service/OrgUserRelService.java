package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.OrgUserRel;

import javax.servlet.http.HttpServletRequest;


public interface OrgUserRelService {

    int insertOrgUserRel(String beanJson, HttpServletRequest request);
    
    int updateOrgUserRel(String beanJson, Long id) ;
    
    int deleteOrgUserRel(Long id);
    
    int batchDeleteOrgUserRel(String ids);
    
    /**
     *  新增机构用户关联关系,反显id
     */
    OrgUserRel addOrgUserRel(OrgUserRel orgaUserRel) throws Exception;
    
    /**
     *  更新机构用户关联关系
     */
    OrgUserRel updateOrgUserRel(OrgUserRel orgaUserRel);
}
