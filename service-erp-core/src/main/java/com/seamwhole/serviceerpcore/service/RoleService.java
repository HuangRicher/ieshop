package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface RoleService {


    Role getRole(long id);

    List<Role> getRole();

    List<Role> select(String name, int offset, int rows);

    Long countRole(String name);

    int insertRole(String beanJson, HttpServletRequest request);

    int updateRole(String beanJson, Long id);

    int deleteRole(Long id);

    int batchDeleteRole(String ids);

    List<Role> findUserRole();
    
    /**
     *  逻辑删除角色信息
     */
    int batchDeleteRoleByIds(String ids);
}
