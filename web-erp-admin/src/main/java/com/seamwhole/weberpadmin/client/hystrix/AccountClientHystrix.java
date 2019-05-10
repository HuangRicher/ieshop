package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.AccountClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
@Component
public class AccountClientHystrix implements AccountClient {
    @Override
    public String findBySelect(HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo getAccount(HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findAccountInOutList(Integer currentPage, Integer pageSize, Long accountId, BigDecimal initialAmount) {
        return null;
    }

    @Override
    public String updateAmountIsDefault(Boolean isDefault, Long accountId) {
        return null;
    }

    @Override
    public Object batchDeleteAccountByIds(String ids, String deleteType) {
        return null;
    }
}
