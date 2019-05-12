package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.AccountClient;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;



@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Resource
    private AccountClient accountClient;

    /**
     * 查找结算账户信息-下拉框
     */
    @GetMapping(value = "/findBySelect")
    public String findBySelect() throws Exception {
        String res =accountClient.findBySelect();
        return res;
    }

    /**
     * 获取所有结算账户
     */
    @GetMapping(value = "/getAccount")
    public BaseResponseInfo getAccount() throws Exception {
        BaseResponseInfo res =accountClient.getAccount();
        return res;
    }


    /**
     * 账户流水信息
     */
    @GetMapping(value = "/findAccountInOutList")
    public BaseResponseInfo findAccountInOutList(@RequestParam("currentPage") Integer currentPage,
                                                 @RequestParam("pageSize") Integer pageSize,
                                                 @RequestParam("accountId") Long accountId,
                                                 @RequestParam("initialAmount") BigDecimal initialAmount) throws Exception{
        BaseResponseInfo res = accountClient.findAccountInOutList(currentPage,pageSize,accountId,initialAmount);
        return res;
    }


    @PostMapping(value = "/updateAmountIsDefault")
    public String updateAmountIsDefault(@RequestParam("isDefault") Boolean isDefault,
                                        @RequestParam("accountId") Long accountId) throws Exception{
        return accountClient.updateAmountIsDefault(isDefault,accountId);
    }


    /**
     *  批量删除账户信息
     */
    @RequestMapping(value = "/batchDeleteAccountByIds")
    public Object batchDeleteAccountByIds(@RequestParam("ids") String ids,
                                          @RequestParam(value="deleteType", required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)
                                                  String deleteType) throws Exception {

        return accountClient.batchDeleteAccountByIds(ids,deleteType);
    }

}
