package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.mapper.SysUserMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysUserExtMapper;
import com.seamwhole.servicetradecore.model.SysUser;
import com.seamwhole.servicetradecore.model.SysUserExample;
import com.seamwhole.servicetradecore.service.SysRoleService;
import com.seamwhole.servicetradecore.service.SysUserRoleService;
import com.seamwhole.servicetradecore.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author 李鹏军
 * @email 939961241@qq.com
 * @date 2016年12月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserExtMapper sysUserExtMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserDao.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    public SysUser queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    public SysUser queryObject(Long userId) {
        return sysUserDao.queryObject(userId);
    }

    @Override
    public List<SysUser> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SysUser user) {
        user.setCreateTime(new Date());
        //sha256加密
        user.setPassword(Constant.DEFAULT_PASS_WORD;
        sysUserMapper.insertSelective(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUser user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(Constant.DEFAULT_PASS_WORD;
        } else {
            user.setPassword(user.getPassword());
        }
        sysUserDao.update(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] userId) {
        sysUserDao.deleteBatch(userId);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUser user) {
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new CheckException("新增用户所选角色，不是本人创建");
        }
    }


    @Override
    public Page<SysUser> findPage(String name, Integer pageNum, Integer pageSize) {
        SysUserExample example=new SysUserExample();
        example.createCriteria().andUsernameLike("%"+name+"%");
        PageHelper.startPage(pageNum,pageSize);
        sysUserDao.queryListByBean(userWindowDto);
        return PageHelper.endPage();
    }
}
