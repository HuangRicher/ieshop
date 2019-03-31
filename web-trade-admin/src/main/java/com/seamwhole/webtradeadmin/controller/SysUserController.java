package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.except.CheckException;
import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.constant.Constant;
import com.seamwhole.webtradeadmin.info.SysUserDO;
import com.seamwhole.webtradeadmin.info.SysUserInfo;
import com.seamwhole.webtradeadmin.info.SysUserModel;
import com.seamwhole.webtradeadmin.service.SysUserRoleMenuService;
import com.seamwhole.webtradeadmin.shiro.ShiroUtils;
import com.seamwhole.webtradeadmin.util.ResourceUtil;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import com.seamwhole.webtradeadmin.util.StringDataUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.seamwhole.webtradeadmin.shiro.ShiroUtils.getUserId;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        SysUserModel userModel=new SysUserModel();
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", ShiroUtils.getUserEntity().getUserId());
        }
        userModel.setPageNum(Integer.valueOf((String)params.get("page")));
        userModel.setPageSize(Integer.valueOf((String)params.get("limit")));
        //查询列表数据
        PagesInfo<SysUserDO> page = sysUserRoleMenuService.queryUserByPage(userModel);
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public ResponseObject info() {
        return ResponseObject.ok().put("user", ShiroUtils.getUserEntity());
    }

    /**
     * 修改登录用户密码
     */
    @RequestMapping("/password")
    public ResponseObject password(String password, String newPassword) {
        if(ResourceUtil.getConfigByName("sys.demo").equals("1")){
            throw new CheckException("演示环境无法修改密码！");
        }
        if(StringUtils.isBlank(newPassword))
            throw new CheckException("新密码不为能空");
        //MD5加密
        password = StringDataUtils.md5(password,ShiroUtils.getUserEntity().getUsername());;
        //MD5加密
        newPassword = StringDataUtils.md5(newPassword,ShiroUtils.getUserEntity().getUsername());
        //更新密码
        int count = sysUserRoleMenuService.updatePassword(ShiroUtils.getUserEntity().getUserId(), password, newPassword);
        if (count == 0) {
            return ResponseObject.error("原密码不正确");
        }
        //退出
        ShiroUtils.logout();
        return ResponseObject.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public ResponseObject info(@PathVariable("userId") Long userId) {
        SysUserInfo user = sysUserRoleMenuService.getUserInfoById(userId);
        return ResponseObject.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public ResponseObject save(@RequestBody SysUserModel user) {
        user.setUserId(ShiroUtils.getUserEntity().getUserId());
        user.setPassword(StringDataUtils.md5(Constant.DEFAULT_PASS_WORD,user.getUsername()));
        sysUserRoleMenuService.saveUser(user);
        return ResponseObject.ok();
    }

    /**
     * 修改用户
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public ResponseObject update(@RequestBody SysUserModel user) {

        user.setCreateUserId(ShiroUtils.getUserEntity().getUserId());
        sysUserRoleMenuService.updateUser(user);

        return ResponseObject.ok();
    }

    /**
     * 删除用户
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public ResponseObject delete(@RequestBody Long[] ids) {
        if (ArrayUtils.contains(ids, 1L)) {
            return ResponseObject.error("系统管理员不能删除");
        }
        if (ArrayUtils.contains(ids, getUserId())) {
            return ResponseObject.error("当前用户不能删除");
        }
        SysUserModel user=new SysUserModel();
        user.setUserIds(ids);
        sysUserRoleMenuService.deleteUserBatch(user);
        return ResponseObject.ok();
    }
}
