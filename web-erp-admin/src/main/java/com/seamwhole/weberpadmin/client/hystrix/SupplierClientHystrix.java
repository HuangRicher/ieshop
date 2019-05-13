package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.SupplierClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Component
public class SupplierClientHystrix implements SupplierClient{
    @Override
    public String updateAdvanceIn(Long supplierId, BigDecimal advanceIn) {
        return null;
    }

    @Override
    public JSONArray findBySelectCus() {
        return null;
    }

    @Override
    public JSONArray findBySelectSup() {
        return null;
    }

    @Override
    public JSONArray findBySelectRetail() {
        return null;
    }

    @Override
    public BaseResponseInfo findById(Long supplierId) {
        return null;
    }

    @Override
    public String batchSetEnable(Boolean enabled, String supplierIDs) {
        return null;
    }

    @Override
    public JSONArray findUserCustomer(String type, String keyId) {
        return null;
    }

    @Override
    public BaseResponseInfo exportExcel(String supplier, String type, String phonenum, String telephone, String description) {
        return null;
    }

    @Override
    public void importExcelVendor(MultipartFile supplierFile) {

    }

    @Override
    public void importExcelCustomer(MultipartFile supplierFile) {

    }

    @Override
    public void importExcelMember(MultipartFile supplierFile) {

    }

    @Override
    public Object batchDeleteSupplierByIds(String ids, String deleteType) {
        return null;
    }

    @Override
    public List<Supplier> findByAll(String supplier, String type, String phonenum, String telephone, String description) {
        return null;
    }
}
