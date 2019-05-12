package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.hystrix.PersonClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = PersonClientHystrix.class)
public interface PersonClient {


    @GetMapping(value = "/person/getAllList")
    BaseResponseInfo getAllList();

    /**
     * 根据Id获取经手人信息
     */
    @GetMapping(value = "/person/getPersonByIds")
    BaseResponseInfo getPersonByIds(@RequestParam("personIDs") String personIDs);

    /**
     * 根据类型获取经手人信息
     */
    @GetMapping(value = "/person/getPersonByType")
    BaseResponseInfo getPersonByType(@RequestParam("type") String type);

    /**
     * 根据类型获取经手人信息 1-业务员，2-仓管员，3-财务员
     */
    @PostMapping(value = "/person/getPersonByNumType")
    JSONArray getPersonByNumType(@RequestParam("type") String typeNum);

    /**
     *  批量删除经手人信息
     */
    @PostMapping(value = "/person/batchDeletePersonByIds")
    Object batchDeletePersonByIds(@RequestParam("ids") String ids,
                                  @RequestParam("deleteType")String deleteType) ;
}
