package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.hystrix.FunctionsClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = FunctionsClientHystrix.class)
public interface FunctionsClient {

    @PostMapping(value = "/functions/findMenu")
    JSONArray findMenu(@RequestParam(value="pNumber") String pNumber,
                       @RequestParam(value="hasFunctions") String hasFunctions);


    /**
     * 角色对应功能显示
     */
    @PostMapping(value = "/functions/findRoleFunctions/{type}/{keyId}/{loginName}")
    JSONArray findRoleFunctions(@PathVariable("type") String type,
                                @PathVariable("keyId") String keyId,
                                @PathVariable("loginName") String loginName);


    /**
     * 根据id列表查找功能信息
     */
    @GetMapping(value = "/functions/findByIds")
    BaseResponseInfo findByIds(@RequestParam("functionsIds") String functionsIds);


    /**
     *  批量删除功能模块信息
     */
    @PostMapping(value = "/functions/batchDeleteFunctionsByIds")
    Object batchDeleteFunctionsByIds(@RequestParam("ids") String ids);
}
