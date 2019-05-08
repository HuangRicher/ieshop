package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface RoleService {


    Role getRole(long id)throws Exception;

    List<Role> getRole()throws Exception;

    List<Role> select(String name, int offset, int rows)throws Exception;

    Long countRole(String name)throws Exception;

    int insertRole(String beanJson, HttpServletRequest request)throws Exception;

    int updateRole(String beanJson, Long id)throws Exception;

    int deleteRole(Long id)throws Exception;

    int batchDeleteRole(String ids)throws Exception;

    List<Role> findUserRole()throws Exception;
    
    /**
     *  逻辑删除角色信息
     */
    int batchDeleteRoleByIds(String ids)throws Exception;
}
