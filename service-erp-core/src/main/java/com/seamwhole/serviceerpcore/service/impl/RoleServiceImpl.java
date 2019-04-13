package com.seamwhole.serviceerpcore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.mapper.RoleMapper;
import com.seamwhole.serviceerpcore.mapper.ext.RoleExtMapper;
import com.seamwhole.serviceerpcore.model.Role;
import com.seamwhole.serviceerpcore.model.RoleExample;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.service.RoleService;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleExtMapper roleExtMapper;
    @Resource
    private LogServiceImpl logService;
    @Resource
    private UserServiceImpl userService;

    public Role getRole(long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public List<Role> getRole() {
        RoleExample example = new RoleExample();
        return roleMapper.selectByExample(example);
    }

    public List<Role> select(String name, int offset, int rows) {
        return roleExtMapper.selectByConditionRole(name, offset, rows);
    }

    public Long countRole(String name) {
        return roleExtMapper.countsByRole(name);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertRole(String beanJson, HttpServletRequest request) {
        Role role = JSONObject.parseObject(beanJson, Role.class);
        return roleMapper.insertSelective(role);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateRole(String beanJson, Long id) {
        Role role = JSONObject.parseObject(beanJson, Role.class);
        role.setId(id);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteRole(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteRole(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        RoleExample example = new RoleExample();
        example.createCriteria().andIdIn(idList);
        return roleMapper.deleteByExample(example);
    }

    public List<Role> findUserRole(){
        RoleExample example = new RoleExample();
        example.setOrderByClause("Id");
        example.createCriteria().andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<Role> list = roleMapper.selectByExample(example);
        return list;
    }
    /**
     * create by: qiankunpingtai
     * website：https://qiankunpingtai.cn
     * description:
     *  逻辑删除角色信息
     * create time: 2019/3/28 15:44
     * @Param: ids
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteRoleByIds(String ids) {
        logService.insertLog(BusinessConstants.LOG_INTERFACE_NAME_SERIAL_NUMBER,
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_DELETE).append(ids).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User userInfo=userService.getCurrentUser();
        String [] idArray=ids.split(",");
        return roleExtMapper.batchDeleteRoleByIds(new Date(),userInfo==null?null:userInfo.getId(),idArray);
    }
}
