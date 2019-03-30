package com.seamwhole.servicetradecore.controller;


import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.SysUserModel;
import com.seamwhole.servicetradecore.domain.SysUserInfo;
import com.seamwhole.servicetradecore.mapper.model.SysUserDO;
import com.seamwhole.servicetradecore.model.SysUser;
import com.seamwhole.servicetradecore.service.SysUserRoleService;
import com.seamwhole.servicetradecore.service.SysUserService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/old/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    public ResponseObject list(@RequestBody SysUserModel sysUserModel) {
        //只有超级管理员，才能查看所有管理员列表
        Map<String,Object> params=new HashMap<>();
        if (sysUserModel.getRoleId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", sysUserModel.getUserId());
        }
        if(StringUtils.isNotBlank(sysUserModel.getUsername())){
            params.put("username",sysUserModel.getUsername());
        }
        //查询列表数据
        PageInfo<SysUserDO> page=sysUserService.queryByPage(params,sysUserModel.getPageNum(),sysUserModel.getPageSize());
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public ResponseObject info(@RequestBody SysUserModel sysUserModel) {
        SysUser user = sysUserService.queryObject(sysUserModel.getUserId());
        return ResponseObject.ok().put("user", user);
    }

    /**
     * 修改登录用户密码
     */
    @RequestMapping("/password")
    public ResponseObject password(@RequestBody SysUserModel sysUserModel) {
        if(StringUtils.isBlank(sysUserModel.getNewPassword()))
            throw new CheckException("新密码不为能空");
        //sha256加密
        //password = new Sha256Hash(password).toHex();
        //sha256加密
        //newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = sysUserService.updatePassword(sysUserModel.getUserId(), sysUserModel.getPassword(), sysUserModel.getNewPassword());
        if (count == 0) {
            return ResponseObject.error("原密码不正确");
        }
        //退出
        //ShiroUtils.logout();
        return ResponseObject.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    public ResponseObject info(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.queryObject(userId);
        SysUserInfo info=new SysUserInfo();
        BeanUtils.copyProperties(user,info);
        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        info.setRoleIdList(roleIdList);
        return ResponseObject.ok().put("user", info);
    }

    /**
     * 保存用户
     */
    @RequestMapping("/save")
    public ResponseObject save(@RequestBody SysUserModel sysUserModel) {
        sysUserService.save(sysUserModel);
        return ResponseObject.ok();
    }

    /**
     * 修改用户
     */
    @RequestMapping("/update")
    public ResponseObject update(@RequestBody SysUserModel sysUserModel) {
        sysUserService.update(sysUserModel);
        return ResponseObject.ok();
    }

    /**
     * 删除用户
     */
    @RequestMapping("/delete")
    public ResponseObject delete(@RequestBody SysUserModel sysUserModel) {
        if (ArrayUtils.contains(sysUserModel.getUserIds(), 1L)) {
            return ResponseObject.error("系统管理员不能删除");
        }
        if (ArrayUtils.contains(sysUserModel.getUserIds(), sysUserModel.getUserId())) {
            return ResponseObject.error("当前用户不能删除");
        }
        sysUserService.deleteBatch(sysUserModel.getUserIds());
        return ResponseObject.ok();
    }
}
