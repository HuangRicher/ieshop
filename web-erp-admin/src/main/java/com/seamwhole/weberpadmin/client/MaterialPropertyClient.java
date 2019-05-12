package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.MaterialPropertyClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = MaterialPropertyClientHystrix.class)
public interface MaterialPropertyClient {


    /**
     *  批量删除商品扩展信息
     */
    @PostMapping(value = "/materialProperty/batchDeleteMaterialPropertyByIds")
    Object batchDeleteMaterialPropertyByIds(@RequestParam("ids") String ids);
}
