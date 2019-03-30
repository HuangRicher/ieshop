package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SysRoleDO;
import com.seamwhole.webtradeadmin.info.SysRoleInfo;
import com.seamwhole.webtradeadmin.info.SysRoleModel;
import com.seamwhole.webtradeadmin.service.impl.SysRoleServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SysRoleServiceHystrix.class)
public interface SysRoleService {

    @PostMapping(value = "/sysRole/queryRoleByPage",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    PagesInfo<SysRoleDO> queryRoleByPage(@RequestBody SysRoleModel roleModel);

    @PostMapping(value = "/sysRole/queryRoleList",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<SysRoleDO> queryRoleList(@RequestBody SysRoleModel roleModel);

    @GetMapping(value = "/sysRole/getRoleInfoById/{roleId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    SysRoleInfo getRoleInfoById(@PathVariable("roleId") Long roleId);

    @PostMapping(value = "/sysRole/saveRole",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void saveRole(@RequestBody SysRoleModel roleModel);

    @PostMapping(value = "/sysRole/updateRole",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void updateRole(@RequestBody SysRoleModel roleModel);

    @PostMapping(value = "/sysRole/deleteRoleBatch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void deleteRoleBatch(@RequestBody Long[] roleIds);
}
