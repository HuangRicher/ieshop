package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SysMenuInfo;
import com.seamwhole.webtradeadmin.info.SysUserDO;
import com.seamwhole.webtradeadmin.info.SysUserInfo;
import com.seamwhole.webtradeadmin.info.SysUserModel;
import com.seamwhole.webtradeadmin.service.impl.SysUserMenuServiceHystrix;
import com.seamwhole.webtradeadmin.shiro.SysMenu;
import com.seamwhole.webtradeadmin.shiro.SysMenuDO;
import com.seamwhole.webtradeadmin.shiro.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysUserMenuServiceHystrix.class)
public interface SysUserRoleMenuService {

    @GetMapping(value = "/sysUser/queryByUserName/{username}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    SysUser queryByUserName(@PathVariable("username") String username);

    @GetMapping(value = "/sysUser/queryAllPerms/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<String> queryAllPerms(@PathVariable("userId")Long userId);

    @PostMapping(value = "/sysUser/updateSysUserStatus",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateSysUserStatus(@RequestBody SysUser user);

    @PostMapping(value = "/sysUser/queryUserByPage",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    PagesInfo<SysUserDO> queryUserByPage(@RequestBody SysUserModel userModel);

    @PostMapping(value = "/sysUser/updatePassword/{userId}/{password}/{newPassword}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    int updatePassword(@PathVariable("userId") Long userId,
                              @PathVariable("password") String password,
                              @PathVariable("newPassword") String newPassword);

    @GetMapping(value = "/sysUser/getUserInfoById/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    SysUserInfo getUserInfoById(@PathVariable("userId")Long userId);

    @PostMapping(value = "/sysUser/saveUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void saveUser(@RequestBody SysUserModel user);

    @PostMapping(value = "/sysUser/updateUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateUser(@RequestBody SysUserModel user);

    @PostMapping(value = "/sysUser/deleteUserBatch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteUserBatch(@RequestBody SysUserModel user);

    /**-----------------------------------------------------------------------**/

    @GetMapping(value = "/sysMenu/queryList",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<SysMenuDO> queryMenuList();

    @PostMapping(value = "/sysMenu/queryMenusByPage",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    PagesInfo<SysMenuDO> queryMenusByPage(@RequestBody Map<String, Object> params);

    @PostMapping(value = "/sysMenu/queryMenuList",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<SysMenuDO> queryMenuList(@RequestBody Map<String, Object> params);

    @GetMapping(value = "/sysMenu/queryNotButtonMenuList",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<SysMenuInfo> queryNotButtonMenuList();

    @GetMapping(value = "/sysMenu/getMenuById/{menuId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    SysMenu getMenuById(@PathVariable("menuId") Long menuId);

    @PostMapping(value = "/sysMenu/saveMenu",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void saveMenu(@RequestBody SysMenu menu);

    @PostMapping(value = "/sysMenu/updateMenuById",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateMenuById(@RequestBody SysMenu menu);

    @PostMapping(value = "/sysMenu/deleteMenuBatch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteMenuBatch(@RequestBody Long[] menuIds);

    @GetMapping(value = "/sysMenu/getUserMenuList/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<SysMenuInfo> getUserMenuList(@PathVariable("userId") Long userId);

    /**-----------------------------------------------------------------------**/


}
