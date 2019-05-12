package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.UnitClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = UnitClientHystrix.class)
public interface UnitClient {

    /**
     *  批量删除系统配置信息
     */
    @PostMapping(value = "/unit/batchDeleteUnitByIds")
    Object batchDeleteUnitByIds(@RequestParam("ids") String ids,
                                @RequestParam("deleteType")String deleteType);
}
