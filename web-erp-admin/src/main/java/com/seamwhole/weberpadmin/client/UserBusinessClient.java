package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.UserBusinessClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = UserBusinessClientHystrix.class)
public interface UserBusinessClient {

    @GetMapping(value = "/userBusiness/getBasicData")
    BaseResponseInfo getBasicData(@RequestParam(value = "KeyId") String keyId,
                                  @RequestParam(value = "Type") String type);

    @GetMapping(value = "/userBusiness/checkIsValueExist")
    String checkIsValueExist(@RequestParam("type") String type,
                             @RequestParam("keyId") String keyId);

    /**
     * 更新角色的按钮权限
     */
    @PostMapping(value = "/userBusiness/updateBtnStr")
    BaseResponseInfo updateBtnStr(@RequestParam("userBusinessId") Long userBusinessId,
                                  @RequestParam(value ="btnStr") String btnStr);


    /**
     *  批量删除用户角色模块关系信息
     */
    @PostMapping(value = "/userBusiness/batchDeleteUserBusinessByIds")
    Object batchDeleteUserBusinessByIds(@RequestParam("ids") String ids) ;
}
