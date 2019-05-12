package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.SupplierClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@Component
public class SupplierClientHystrix implements SupplierClient{
    @Override
    public String updateAdvanceIn(Long supplierId, BigDecimal advanceIn, HttpServletRequest request) {
        return null;
    }

    @Override
    public JSONArray findBySelectCus(HttpServletRequest request) {
        return null;
    }

    @Override
    public JSONArray findBySelectSup(HttpServletRequest request) {
        return null;
    }

    @Override
    public JSONArray findBySelectRetail(HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findById(Long supplierId, HttpServletRequest request) {
        return null;
    }

    @Override
    public String batchSetEnable(Boolean enabled, String supplierIDs, HttpServletRequest request) {
        return null;
    }

    @Override
    public JSONArray findUserCustomer(String type, String keyId, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo exportExcel(String supplier, String type, String phonenum, String telephone, String description, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public void importExcelVendor(MultipartFile supplierFile, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void importExcelCustomer(MultipartFile supplierFile, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void importExcelMember(MultipartFile supplierFile, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public Object batchDeleteSupplierByIds(String ids, String deleteType) {
        return null;
    }
}
