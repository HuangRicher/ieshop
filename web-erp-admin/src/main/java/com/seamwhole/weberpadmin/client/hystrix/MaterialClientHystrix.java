package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.MaterialClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MaterialClientHystrix implements MaterialClient{
    @Override
    public String checkIsExist(Long id, String name, String model, String color, String standard, String mfrs, String otherField1, String otherField2, String otherField3, String unit, Long unitId, HttpServletRequest request) {
        return null;
    }

    @Override
    public String batchSetEnable(Boolean enabled, String materialIDs, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findById(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public JSONArray findBySelect(String mpList, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findByOrder(HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo exportExcel(String name, String model, Long categoryId, String categoryIds, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public void importExcel(MultipartFile materialFile, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public String getMaterialEnableSerialNumberList(Integer pageSize, Integer currentPage, String search) {
        return null;
    }

    @Override
    public Object batchDeleteMaterialByIds(String ids, String deleteType) {
        return null;
    }
}
