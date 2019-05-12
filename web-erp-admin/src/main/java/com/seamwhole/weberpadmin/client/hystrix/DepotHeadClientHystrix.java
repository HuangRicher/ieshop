package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.DepotHeadClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Component
public class DepotHeadClientHystrix implements DepotHeadClient{
    @Override
    public String batchSetStatus(String status, String depotHeadIDs, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo buildNumber(HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo getMaxId(HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findByMonth(String monthTime, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findInDetail(Integer currentPage, Integer pageSize, Integer oId, Integer pid, String dids, String beginTime, String endTime, String type, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findInOutMaterialCount(Integer currentPage, Integer pageSize, Integer oId, Integer pid, String dids, String beginTime, String endTime, String type, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findStatementAccount(Integer currentPage, Integer pageSize, String beginTime, String endTime, Integer organId, String supType, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findTotalPay(Integer supplierId, String endTime, String supType, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo getDetailByNumber(String number, HttpServletRequest request) {
        return null;
    }

    @Override
    public Object addDepotHeadAndDetail(String beanJson, String inserted, String deleted, String updated, HttpServletRequest request) {
        return null;
    }

    @Override
    public Object updateDepotHeadAndDetail(Long id, String beanJson, String inserted, String deleted, String updated, BigDecimal preTotalPrice) {
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
