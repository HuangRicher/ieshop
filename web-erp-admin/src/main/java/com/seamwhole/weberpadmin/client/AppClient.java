package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.weberpadmin.client.hystrix.AppClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = AppClientHystrix.class)
public interface AppClient {

    /**
     * 根据用户查询有权限的app
     */
    @GetMapping(value = "/app/findAppByUserId")
    JSONObject findAppByUserId(@RequestParam("userId") String userId);

    @GetMapping(value = "/app/findDesk")
    JSONObject findDesk();

    /**
     * 角色对应应用显示
     */
    @PostMapping(value = "/app/findRoleAPP/{type}/{keyId}/{loginName}")
    JSONArray findRoleAPP(@PathVariable("type") String type, @PathVariable("keyId") String keyId,@PathVariable("loginName")String loginName);

    /**
     * 上传图片
     */
    @PostMapping(value = "/app/uploadImg")
    BaseResponseInfo uploadImg(MultipartFile fileInfo, @RequestParam("fileInfoName") String fileName);


    /**
     *  批量删除应用信息
     */
    @PostMapping(value = "/app/batchDeleteAppByIds")
    Object batchDeleteAppByIds(@RequestParam("ids") String ids) ;
}
