package com.seamwhole.servicetradecore.controller;

import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.controller.model.UserModel;
import com.seamwhole.servicetradecore.service.UserService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册
 */
@Api(tags = "注册")
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @ApiOperation(value = "注册")
    @PostMapping("register")
    public ResponseObject register(@RequestBody UserModel user) {
        if(StringUtils.isBlank(user.getMobile()))
            throw new CheckException("手机号不能为空");
        if(StringUtils.isBlank(user.getPassword()))
            throw new CheckException("密码不能为空");

        userService.save(user.getMobile(), user.getPassword());
        return ResponseObject.ok();
    }
}
