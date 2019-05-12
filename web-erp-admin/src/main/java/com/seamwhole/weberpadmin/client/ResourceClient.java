package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.weberpadmin.client.hystrix.ResourceClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = ResourceClientHystrix.class)
public interface ResourceClient {


    @GetMapping(value = "/test/heart")
    JSONObject exitHeart();

    @GetMapping(value = "/{apiName}/list/{pageSize}/{currentPage}/{search}")
    String getList(@PathVariable("apiName") String apiName,
                   @PathVariable(value = Constants.PAGE_SIZE) Integer pageSize,
                   @PathVariable(value = Constants.CURRENT_PAGE) Integer currentPage,
                   @PathVariable(value = Constants.SEARCH) String search);

    @PostMapping(value = "/{apiName}/add")
    String addResource(@PathVariable("apiName") String apiName,
                       @RequestParam("info") String beanJson);

    @PostMapping(value = "/{apiName}/update")
    String updateResource(@PathVariable("apiName") String apiName,
                          @RequestParam("info") String beanJson,
                          @RequestParam("id") Long id);

    @PostMapping(value = "/{apiName}/{id}/delete")
    String deleteResource(@PathVariable("apiName") String apiName,
                          @PathVariable("id") Long id);

    @PostMapping(value = "/{apiName}/batchDelete")
    String batchDeleteResource(@PathVariable("apiName") String apiName,
                               @RequestParam("ids") String ids);

    @GetMapping(value = "/{apiName}/checkIsNameExist")
    String checkIsNameExist(@PathVariable("apiName") String apiName,
                            @RequestParam ("id")Long id,
                            @RequestParam("name") String name);


}
