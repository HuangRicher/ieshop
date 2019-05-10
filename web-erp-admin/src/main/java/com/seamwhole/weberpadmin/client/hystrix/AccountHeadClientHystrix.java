package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.AccountHeadClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

@Component
public class AccountHeadClientHystrix implements AccountHeadClient {
    @Override
    public BaseResponseInfo getMaxId() {
        return null;
    }

    @Override
    public BaseResponseInfo findTotalPay(Integer supplierId, String endTime, String supType) {
        return null;
    }

    @Override
    public BaseResponseInfo getDetailByNumber(String billNo) {
        return null;
    }

    @Override
    public Object batchDeleteAccountHeadByIds(String ids, String deleteType) {
        return null;
    }
}
