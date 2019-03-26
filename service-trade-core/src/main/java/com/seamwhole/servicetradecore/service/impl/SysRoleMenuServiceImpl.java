package com.seamwhole.servicetradecore.service.impl;


import com.seamwhole.servicetradecore.mapper.SysRoleMenuMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysRoleMenuExtMapper;
import com.seamwhole.servicetradecore.model.SysRoleMenuExample;
import com.seamwhole.servicetradecore.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色与菜单对应关系
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:44:35
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysRoleMenuExtMapper sysRoleMenuExtMapper;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        if (menuIdList.size() == 0) {
            return;
        }
        //先删除角色与菜单关系
        SysRoleMenuExample example=new SysRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        sysRoleMenuMapper.deleteByExample(example);

        //保存角色与菜单关系

        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        sysRoleMenuExtMapper.insertBatch(map);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return sysRoleMenuExtMapper.queryMenuIdList(roleId);
    }

}
