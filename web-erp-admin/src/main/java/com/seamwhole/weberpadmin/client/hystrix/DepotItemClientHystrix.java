package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.DepotItemClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DepotItemClientHystrix implements DepotItemClient{
    @Override
    public BaseResponseInfo getHeaderIdByMaterial(String materialParam, String depotIds, HttpServletRequest request) {
        return null;
    }

    @Override
    public String findDetailByTypeAndMaterialId(Integer pageSize, Integer currentPage, String mId, HttpServletRequest request) {
        return null;
    }

    @Override
    public String findStockNumById(Integer pageSize, Integer currentPage, Integer pid, String mId, String monthTime, HttpServletRequest request) {
        return null;
    }

    @Override
    public String findStockNumByMaterialId(Integer pageSize, Integer currentPage, String mId, String monthTime, HttpServletRequest request) {
        return null;
    }

    @Override
    public String saveDetials(String inserted, String deleted, String updated, Long headerId, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo getDetailList(Long headerId, String mpList, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo findByAll(Integer currentPage, Integer pageSize, Integer projectId, String monthTime, String headIds, String materialIds, String mpList, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo totalCountMoney(Integer pid, String monthTime, String headIds, String materialIds, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo buyIn(Integer currentPage, Integer pageSize, String monthTime, String headIds, String materialIds, String mpList, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo saleOut(Integer currentPage, Integer pageSize, String monthTime, String headIds, String materialIds, String mpList, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo exportExcel(Integer currentPage, Integer pageSize, Integer projectId, String monthTime, String headIds, String materialIds, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Object batchDeleteDepotItemByIds(String ids) {
        return null;
    }

    @Override
    public BaseResponseInfo findStockWarningCount(Integer currentPage, Integer pageSize, Integer pid) {
        return null;
    }

    @Override
    public BaseResponseInfo exportWarningExcel(Integer currentPage, Integer pageSize, Integer projectId, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
