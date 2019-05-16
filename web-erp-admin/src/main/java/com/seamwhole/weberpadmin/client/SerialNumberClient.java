package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.SerialNumberClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.domain.SerialNumberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = SerialNumberClientHystrix.class)
public interface SerialNumberClient {


    /**
     *  检查序列号是否存在
     */
    @PostMapping("/serialNumber/checkIsExist")
    Object checkIsExist(@RequestBody SerialNumberInfo serialNumber);


    /**
     *  新增序列号信息
     */
    @PostMapping("/serialNumber/addSerialNumber")
    Object addSerialNumber(@RequestParam("info") String beanJson);

    /**
     *  修改序列号信息
     */
    @PostMapping("/serialNumber/updateSerialNumber")
    Object updateSerialNumber(@RequestParam("info") String beanJson);


    /**
     *批量添加序列号
     */
    @PostMapping("/serialNumber/batAddSerialNumber/{materialName}/{serialNumberPrefix}/{batAddTotal}/{remark}")
    Object batAddSerialNumber(@PathVariable("materialName") String materialName,
                              @PathVariable("serialNumberPrefix") String serialNumberPrefix,
                              @PathVariable("batAddTotal") Integer batAddTotal,
                              @PathVariable("remark") String remark);

    /**
     *  逻辑删除序列号信息
     */
    @PostMapping(value = "/serialNumber/batchDeleteSerialNumberByIds")
    Object batchDeleteSerialNumberByIds(@RequestParam("ids") String ids);

}
