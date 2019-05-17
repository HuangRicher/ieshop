package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.AccountItemClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.domain.AccountItemInfo;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = AccountItemClientHystrix.class)
public interface AccountItemClient {


    /**
     *  业务逻辑操作放在service层，controller只做参数解析和视图封装
     */
    @PostMapping(value = "/accountItem/saveDetails")
    String saveDetails(@RequestBody AccountItemInfo info);

    @GetMapping(value = "/accountItem/getDetailList")
    BaseResponseInfo getDetailList(@RequestParam("headerId") Long headerId);

    /**
     *  批量删除财务明细信息
     */
    @PostMapping(value = "/accountItem/batchDeleteAccountItemByIds")
    Object batchDeleteAccountItemByIds(@RequestParam("ids") String ids);

}
