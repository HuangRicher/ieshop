package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.SysUserModel;
import com.seamwhole.servicetradecore.mapper.SysUserMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysUserExtMapper;
import com.seamwhole.servicetradecore.mapper.model.SysUserDO;
import com.seamwhole.servicetradecore.model.SysUser;
import com.seamwhole.servicetradecore.model.SysUserExample;
import com.seamwhole.servicetradecore.service.SysRoleService;
import com.seamwhole.servicetradecore.service.SysUserRoleService;
import com.seamwhole.servicetradecore.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    /*@Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserDao.queryAllPerms(userId);
    }*/

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserExtMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUser queryByUserName(String username) {
        SysUserExample example=new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUser> list=sysUserMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list))
            return null;
        else
            return list.get(0);
    }

    @Override
    public SysUser queryObject(Long userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }


    @Override
    public PageInfo<SysUserDO> queryByPage(Map<String, Object> param, Integer pageNum, Integer pageSize) {
        Page<SysUserDO> page=PageHelper.startPage(pageNum,pageSize);
        sysUserExtMapper.queryList(param);
        return page.toPageInfo();
    }

    @Override
    @Transactional
    public void save(SysUserModel sysUserModel) {
        SysUser user=new SysUser();
        user.setEmail(sysUserModel.getEmail());
        user.setMobile(sysUserModel.getMobile());
        user.setDeptId(sysUserModel.getDeptId());
        user.setUsername(sysUserModel.getUsername());
        user.setCreateUserId(sysUserModel.getUserId());
        user.setCreateTime(new Date());
        //sha256加密
        user.setPassword(Constant.DEFAULT_PASS_WORD);
        sysUserMapper.insertSelective(user);

        //检查角色是否越权
        checkRole(sysUserModel.getUserId(),sysUserModel.getRoleIdList());

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), sysUserModel.getRoleIdList());
    }


    @Override
    @Transactional
    public void update(SysUserModel sysUserModel) {
        SysUser user=new SysUser();
        user.setEmail(sysUserModel.getEmail());
        user.setMobile(sysUserModel.getMobile());
        user.setDeptId(sysUserModel.getDeptId());
        user.setUsername(sysUserModel.getUsername());
        user.setStatus(sysUserModel.getStatus());
        user.setCreateUserId(sysUserModel.getUserId());
        user.setUserId(sysUserModel.getUid());
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(Constant.DEFAULT_PASS_WORD);
        } else {
            user.setPassword(user.getPassword());
        }
        sysUserMapper.updateByPrimaryKeySelective(user);

        //检查角色是否越权
        checkRole(user.getCreateUserId(),sysUserModel.getRoleIdList());

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), sysUserModel.getRoleIdList());
    }

    @Override
    public void updateSysUserStatus(Long userId, Integer status) {
        SysUserExample example=new SysUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        SysUser user=new SysUser();
        user.setStatus(status);
        sysUserMapper.updateByExampleSelective(user,example);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] userId) {
        sysUserExtMapper.deleteBatch(userId);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        SysUserExample example=new SysUserExample();
        example.createCriteria().andUserIdEqualTo(userId).andPasswordEqualTo(password);
        SysUser user=new SysUser();
        user.setPassword(newPassword);
        return sysUserMapper.updateByExampleSelective(user,example);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(Long userId,List<Long> roleIds) {
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (userId == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(userId);

        //判断是否越权
        if (!roleIdList.containsAll(roleIds)) {
            throw new CheckException("新增用户所选角色，不是本人创建");
        }
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserExtMapper.queryAllPerms(userId);
    }

    /*@Override
    public Page<SysUser> findPage(String name, Integer pageNum, Integer pageSize) {
        SysUserExample example=new SysUserExample();
        example.createCriteria().andUsernameLike("%"+name+"%");
        PageHelper.startPage(pageNum,pageSize);
        sysUserDao.queryListByBean(userWindowDto);
        return PageHelper.endPage();
    }*/
}
