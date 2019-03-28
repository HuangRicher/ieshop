package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.SysUserModel;
import com.seamwhole.servicetradecore.mapper.model.SysUserDO;
import com.seamwhole.servicetradecore.model.SysUser;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:43:39
 */
public interface SysUserService {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    //List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByUserName(String username);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     * @return
     */
    SysUser queryObject(Long userId);

    /**
     * 查询用户列表
     */
    PageInfo<SysUserDO> queryByPage(Map<String, Object> param, Integer pageNum, Integer pageSize);

    /**
     * 保存用户
     */
    void save(SysUserModel user);

    /**
     * 修改用户
     */
    void update(SysUserModel user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    int updatePassword(Long userId, String password, String newPassword);

    List<String> queryAllPerms(Long userId);

    void updateSysUserStatus(Long userId, Integer status);

    /**
     * 根据条件分页查询
     * @param
     * @param pageNum
     * @return
     */
    //Page<SysUser> findPage(String name,Integer pageNum,Integer pageSize);
}
