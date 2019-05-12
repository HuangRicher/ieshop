package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.AccountHeadClient;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/accountHead")
public class AccountHeadController {
    private Logger logger = LoggerFactory.getLogger(AccountHeadController.class);

    @Resource
    private AccountHeadClient accountHeadClient;

    /**
     * 获取最大的id
     */
    @GetMapping(value = "/getMaxId")
    public BaseResponseInfo getMaxId()throws Exception {
        BaseResponseInfo res = accountHeadClient.getMaxId();
        return res;
    }


    /**
     * 查询单位的累计应收和累计应付，收预付款不计入此处
     */
    @GetMapping(value = "/findTotalPay")
    public BaseResponseInfo findTotalPay(@RequestParam("supplierId") Integer supplierId,
                                         @RequestParam("endTime") String endTime,
                                         @RequestParam("supType") String supType)throws Exception {

        BaseResponseInfo res = accountHeadClient.findTotalPay(supplierId,endTime,supType);
        return res;
    }

    /**
     * 根据编号查询单据信息
     */
    @GetMapping(value = "/getDetailByNumber")
    public BaseResponseInfo getDetailByNumber(@RequestParam("billNo") String billNo)throws Exception {
        BaseResponseInfo res = accountHeadClient.getDetailByNumber(billNo);
        return res;
    }


    /**
     *  批量删除账户信息
     */
    @RequestMapping(value = "/batchDeleteAccountHeadByIds")
    public Object batchDeleteAccountHeadByIds(@RequestParam("ids") String ids,
                                              @RequestParam(value="deleteType", required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)
                                                      String deleteType) throws Exception {

        return accountHeadClient.batchDeleteAccountHeadByIds(ids,deleteType);
    }

}
