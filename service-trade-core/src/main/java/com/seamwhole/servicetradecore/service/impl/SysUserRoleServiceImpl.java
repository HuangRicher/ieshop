package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.SysUserRoleMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysUserRoleExtMapper;
import com.seamwhole.servicetradecore.model.SysUserRoleExample;
import com.seamwhole.servicetradecore.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户与角色对应关系
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserRoleExtMapper sysUserRoleExtMapper;


    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if (roleIdList.size() == 0) {
            return;
        }
        //先删除用户与角色关系
        delete(userId);

        //保存用户与角色关系
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        sysUserRoleExtMapper.insertBatch(map);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return sysUserRoleExtMapper.queryRoleIdList(userId);
    }

    @Override
    public void delete(Long userId) {
        SysUserRoleExample example=new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        sysUserRoleMapper.deleteByExample(example);
    }
}
