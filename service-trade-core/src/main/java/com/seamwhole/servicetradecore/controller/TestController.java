package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.service.ShopUserService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API测试接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:47
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private ShopUserService userService;

    /**
     * 获取用户信息
     */
    @PostMapping("userInfo")
    public ResponseObject userInfo(@RequestBody  ShopUser user) {
        return ResponseObject.ok().put("user", user);
    }

    /**
     * 忽略Token验证测试
     */
    @PostMapping("notToken")
    public ResponseObject notToken() {
        return ResponseObject.ok().put("msg", "无需token也能访问。。。");
    }

    /**
     * 根据手机号查询用户信息接口测试方法
     *
     * @param mobile
     * @return
     */
    @PostMapping("userListByMobile")
    public ResponseObject userList(String mobile) {
        ShopUser userEntity = userService.queryByMobile(mobile);
        return ResponseObject.ok().put("dto", userEntity);
    }
}
