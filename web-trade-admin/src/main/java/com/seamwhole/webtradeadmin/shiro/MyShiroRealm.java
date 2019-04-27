package com.seamwhole.webtradeadmin.shiro;


import com.seamwhole.webtradeadmin.constant.Constant;
import com.seamwhole.webtradeadmin.redis.RedisService;
import com.seamwhole.webtradeadmin.service.SysUserRoleMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserRoleMenuService sysUserService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //List<String> permsList = (List<String>) redisService.get(Constant.PERMS_LIST + userId);
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            List<SysMenuDO> menuList = sysUserRoleMenuService.queryMenuList(new HashMap<>());
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuDO menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserRoleMenuService.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<String>();
        if (permsList != null && permsList.size() != 0) {
            for (String perms : permsList) {
                if (StringUtils.isBlank(perms)) {
                    continue;
                }
                permsSet.addAll(Arrays.asList(perms.trim().split(",")));
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        //查询用户信息
        SysUser user = sysUserService.queryByUserName(username);

        //账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        // 把当前用户放入到session中
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        session.setAttribute(Constant.CURRENT_USER, user);

        List<String> permsList;

        //系统管理员，拥有最高权限
        if (Constant.SUPER_ADMIN == user.getUserId()) {
            List<SysMenuDO> menuList = sysUserService.queryMenuList();
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuDO menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserService.queryAllPerms(user.getUserId());
        }
        redisService.set(Constant.PERMS_LIST + user.getUserId(), permsList);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

}