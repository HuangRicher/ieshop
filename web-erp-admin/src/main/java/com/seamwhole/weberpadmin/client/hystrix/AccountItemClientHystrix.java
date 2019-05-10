package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.AccountItemClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

@Component
public class AccountItemClientHystrix implements AccountItemClient {
    @Override
    public String saveDetails(String inserted, String deleted, String updated, Long headerId, String listType) {
        return null;
    }

    @Override
    public BaseResponseInfo getDetailList(Long headerId) {
        return null;
    }

    @Override
    public Object batchDeleteAccountItemByIds(String ids) {
        return null;
    }
}
