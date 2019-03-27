package com.seamwhole.webtradeadmin.service;

import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.service.impl.SysUserMenuServiceHystrix;
import com.seamwhole.webtradeadmin.shiro.SysMenuDO;
import com.seamwhole.webtradeadmin.shiro.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "trade-core-service",configuration = FeignConfig.class,fallback = SysUserMenuServiceHystrix.class)
public interface SysUserMenuService {

    @GetMapping(value = "/sysUser/queryByUserName/{username}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    SysUser queryByUserName(@PathVariable("username") String username);

    @GetMapping(value = "/sysUser/queryAllPerms/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<String> queryAllPerms(@PathVariable("userId")Long userId);

    @GetMapping(value = "/sysMenu/queryList",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<SysMenuDO> queryList();
}
