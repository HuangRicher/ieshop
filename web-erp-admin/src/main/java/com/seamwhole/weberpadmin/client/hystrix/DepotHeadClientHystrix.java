package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.DepotHeadClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.DepotHeadInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Component
public class DepotHeadClientHystrix implements DepotHeadClient{
    @Override
    public String batchSetStatus(String status, String depotHeadIDs) {
        return null;
    }

    @Override
    public BaseResponseInfo buildNumber() {
        return null;
    }

    @Override
    public BaseResponseInfo getMaxId() {
        return null;
    }

    @Override
    public BaseResponseInfo findByMonth(String monthTime) {
        return null;
    }

    @Override
    public BaseResponseInfo findInDetail(DepotHeadInfo depotHeadInfo) {
        return null;
    }

    @Override
    public BaseResponseInfo findInOutMaterialCount(DepotHeadInfo depotHeadInfo) {
        return null;
    }

    @Override
    public BaseResponseInfo findStatementAccount(DepotHeadInfo info) {
        return null;
    }

    @Override
    public BaseResponseInfo findTotalPay(Integer supplierId, String endTime, String supType) {
        return null;
    }

    @Override
    public BaseResponseInfo getDetailByNumber(String number) {
        return null;
    }

    @Override
    public Object addDepotHeadAndDetail(String beanJson, String inserted, String deleted, String updated) {
        return null;
    }

    @Override
    public Object updateDepotHeadAndDetail(DepotHeadInfo info) {
        return null;
    }

    @Override
    public Object deleteDepotHeadAndDetail(Long id) {
        return null;
    }

    @Override
    public Object batchDeleteDepotHeadAndDetail(String ids) {
        return null;
    }
}
