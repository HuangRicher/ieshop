package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.UserBusinessClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/userBusiness")
public class UserBusinessController {
    private Logger logger = LoggerFactory.getLogger(UserBusinessController.class);

    @Resource
    private UserBusinessClient userBusinessClient;

    @GetMapping(value = "/getBasicData")
    public BaseResponseInfo getBasicData(@RequestParam(value = "KeyId") String keyId,
                                         @RequestParam(value = "Type") String type)throws Exception {

        BaseResponseInfo res = userBusinessClient.getBasicData(keyId,type);
        return res;
    }

    @GetMapping(value = "/checkIsValueExist")
    public String checkIsValueExist(@RequestParam(value ="type", required = false) String type,
                                   @RequestParam(value ="keyId", required = false) String keyId)throws Exception {

        return userBusinessClient.checkIsValueExist(type,keyId);
    }

    /**
     * 更新角色的按钮权限
     */
    @PostMapping(value = "/updateBtnStr")
    public BaseResponseInfo updateBtnStr(@RequestParam(value ="userBusinessId", required = false) Long userBusinessId,
                                         @RequestParam(value ="btnStr", required = false) String btnStr)throws Exception {

        BaseResponseInfo res = userBusinessClient.updateBtnStr(userBusinessId,btnStr);
        return res;
    }


    /**
     *  批量删除用户角色模块关系信息
     */
    @RequestMapping(value = "/batchDeleteUserBusinessByIds")
    public Object batchDeleteUserBusinessByIds(@RequestParam("ids") String ids) throws Exception {

        return userBusinessClient.batchDeleteUserBusinessByIds(ids);
    }
}
