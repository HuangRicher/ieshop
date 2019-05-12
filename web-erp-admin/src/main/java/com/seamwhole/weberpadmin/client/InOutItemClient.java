package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.InOutItemClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = InOutItemClientHystrix.class)
public interface InOutItemClient {

    /**
     * 查找收支项目信息-下拉框
     */
    @GetMapping(value = "/inOutItem/findBySelect")
    String findBySelect(@RequestParam("type") String type) ;


    /**
     *  批量删除收支项目信息
     */
    @PostMapping(value = "/inOutItem/batchDeleteInOutItemByIds")
    Object batchDeleteInOutItemByIds(@RequestParam("ids") String ids,
                                     @RequestParam(value="deleteType")String deleteType);

}
