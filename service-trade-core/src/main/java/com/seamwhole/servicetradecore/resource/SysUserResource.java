package com.seamwhole.servicetradecore.resource;

import com.seamwhole.servicetradecore.model.SysUser;
import com.seamwhole.servicetradecore.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysUser")
public class SysUserResource {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/queryByUserName/{username}")
    public SysUser queryByUserName(@PathVariable("username") String username){
        return sysUserService.queryByUserName(username);
    }

    @GetMapping("/queryAllPerms/{userId}")
    List<String> queryAllPerms(@PathVariable("userId")Long userId){
        return sysUserService.queryAllPerms(userId);
    }
}
