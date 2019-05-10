package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.AccountClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = AccountClientHystrix.class)
public interface AccountClient {

    /**
     * 查找结算账户信息-下拉框
     */
    @GetMapping(value = "/account/findBySelect")
    String findBySelect(HttpServletRequest request);

    /**
     * 获取所有结算账户
     */
    @GetMapping(value = "/account/getAccount")
    BaseResponseInfo getAccount(HttpServletRequest request);

    /**
     * 账户流水信息
     */
    @GetMapping(value = "/account/findAccountInOutList")
    BaseResponseInfo findAccountInOutList(@RequestParam("currentPage") Integer currentPage,
                                          @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam("accountId") Long accountId,
                                          @RequestParam("initialAmount") BigDecimal initialAmount);


    @PostMapping(value = "/account/updateAmountIsDefault")
    String updateAmountIsDefault(@RequestParam("isDefault") Boolean isDefault,
                                 @RequestParam("accountId") Long accountId);

    
    /**
     *  批量删除账户信息;
     */
    @PostMapping(value = "/account/batchDeleteAccountByIds")
    Object batchDeleteAccountByIds(@RequestParam("ids") String ids, @RequestParam(value="deleteType",
            required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)String deleteType);

}
