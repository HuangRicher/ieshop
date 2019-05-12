package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.AccountHeadClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = AccountHeadClientHystrix.class)
public interface AccountHeadClient {

    /**
     * 获取最大的id
     */
    @GetMapping(value = "/accountHead/getMaxId")
    BaseResponseInfo getMaxId();

    /**
     * 查询单位的累计应收和累计应付，收预付款不计入此处
     */
    @GetMapping(value = "/accountHead/findTotalPay/{supplierId}/{endTime}/{supType}")
    BaseResponseInfo findTotalPay(@PathVariable("supplierId") Integer supplierId,
                                         @PathVariable("endTime") String endTime,
                                         @PathVariable("supType") String supType);

    /**
     * 根据编号查询单据信息
     */
    @GetMapping(value = "/accountHead/getDetailByNumber")
    BaseResponseInfo getDetailByNumber(@RequestParam("billNo") String billNo);


    /**
     *  批量删除账户信息
     */
    @PostMapping(value = "/accountHead/batchDeleteAccountHeadByIds")
    Object batchDeleteAccountHeadByIds(@RequestParam("ids") String ids, @RequestParam("deleteType")String deleteType);

}
