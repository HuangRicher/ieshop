package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.TreeNode;
import com.seamwhole.serviceerpcore.model.Organization;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface OrganizationService {
    
    int insertOrganization(String beanJson, HttpServletRequest request);
    
    int updateOrganization(String beanJson, Long id);
    
    int deleteOrganization(Long id);
    
    int batchDeleteOrganization(String ids);
    
    int addOrganization(Organization org) throws Exception;
    
    int editOrganization(Organization org)throws Exception;

    List<TreeNode> getOrganizationTree(Long id)throws Exception ;

    List<Organization> findById(Long id) throws Exception;

    List<Organization> findByOrgNo(String orgNo)throws Exception;
    /**
     *  检查机构编号是否已经存在
     */
    void checkOrgNoIsExists(String orgNo,Long id)throws Exception ;
    
    int batchDeleteOrganizationByIds(String ids) throws Exception;
}
