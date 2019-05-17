package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.AccountItemClient;
import com.seamwhole.weberpadmin.domain.AccountItemInfo;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/accountItem")
public class AccountItemController {
    private Logger logger = LoggerFactory.getLogger(AccountItemController.class);

    @Autowired
    private AccountItemClient accountItemClient;

    @PostMapping(value = "/saveDetails")
    public String saveDetails(AccountItemInfo info) throws Exception{

        return accountItemClient.saveDetails(info);
    }



    @GetMapping(value = "/getDetailList")
    public BaseResponseInfo getDetailList(@RequestParam("headerId") Long headerId)throws Exception {
        BaseResponseInfo res = accountItemClient.getDetailList(headerId);
        return res;
    }


    /**
     * 批量删除财务明细信息
     */
    @RequestMapping(value = "/batchDeleteAccountItemByIds")
    public Object batchDeleteAccountItemByIds(@RequestParam("ids") String ids) throws Exception {


        return accountItemClient.batchDeleteAccountItemByIds(ids);
    }

}
