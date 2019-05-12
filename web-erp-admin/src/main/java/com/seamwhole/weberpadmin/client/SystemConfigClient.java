package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.SystemConfigClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = SystemConfigClientHystrix.class)
public interface SystemConfigClient {

    /**
     *  批量删除系统配置信息
     */
    @PostMapping(value = "/systemConfig/batchDeleteSystemConfigByIds")
    Object batchDeleteSystemConfigByIds(@RequestParam("ids") String ids) ;

}
