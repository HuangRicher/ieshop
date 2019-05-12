package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.hystrix.OrganizationClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = OrganizationClientHystrix.class)
public interface OrganizationClient {

    /**
     * 根据id来查询机构信息
     */
    @GetMapping(value = "/organization/findById")
    BaseResponseInfo findById(@RequestParam("id") Long id);

    /**
     * 获取机构树数据
     */
    @GetMapping(value = "/organization/getOrganizationTree")
    JSONArray getOrganizationTree(@RequestParam("id") Long id) ;


    /**
     *  新增机构信息
     */
    @PostMapping(value = "/organization/addOrganization")
    Object addOrganization(@RequestParam("info") String beanJson);


    /**
     *  修改机构信息
     */
    @PostMapping(value = "/organization/editOrganization")
    Object editOrganization(@RequestParam("info") String beanJson);


    /**
     *  批量删除机构信息
     */
    @PostMapping(value = "/organization/batchDeleteOrganization")
    Object batchDeleteOrganization(@RequestParam("ids") String ids);
}
