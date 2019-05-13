package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.DepotItemClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.DepotItemStockWarningCount;
import com.seamwhole.weberpadmin.domain.DepotItemVo4WithInfoEx;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DepotItemClientHystrix implements DepotItemClient{
    @Override
    public BaseResponseInfo getHeaderIdByMaterial(String materialParam, String depotIds) {
        return null;
    }

    @Override
    public String findDetailByTypeAndMaterialId(Integer pageSize, Integer currentPage, String mId) {
        return null;
    }

    @Override
    public String findStockNumById(Integer pageSize, Integer currentPage, Integer pid, String mId, String monthTime) {
        return null;
    }

    @Override
    public String findStockNumByMaterialId(Integer pageSize, Integer currentPage, String mId, String monthTime) {
        return null;
    }

    @Override
    public String saveDetails(String inserted, String deleted, String updated, Long headerId) {
        return null;
    }

    @Override
    public BaseResponseInfo getDetailList(Long headerId, String mpList) {
        return null;
    }

    @Override
    public BaseResponseInfo findByAllByPage(Integer currentPage, Integer pageSize, Integer projectId, String monthTime, String headIds, String materialIds, String mpList) {
        return null;
    }

    @Override
    public BaseResponseInfo totalCountMoney(Integer pid, String monthTime, String headIds, String materialIds) {
        return null;
    }

    @Override
    public BaseResponseInfo buyIn(Integer currentPage, Integer pageSize, String monthTime, String headIds, String materialIds, String mpList) {
        return null;
    }

    @Override
    public BaseResponseInfo saleOut(Integer currentPage, Integer pageSize, String monthTime, String headIds, String materialIds, String mpList) {
        return null;
    }

    @Override
    public BaseResponseInfo exportExcel(Integer currentPage, Integer pageSize, Integer projectId, String monthTime, String headIds, String materialIds) {
        return null;
    }

    @Override
    public Object batchDeleteDepotItemByIds(String ids) {
        return null;
    }

    @Override
    public BaseResponseInfo findStockWarningCountByPage(Integer currentPage, Integer pageSize, Integer pid) {
        return null;
    }

    @Override
    public BaseResponseInfo exportWarningExcel(Integer currentPage, Integer pageSize, Integer projectId) {
        return null;
    }

    @Override
    public List<DepotItemVo4WithInfoEx> findByAll(String headIds, String materialIds, Integer count, Integer pageSize) {
        return null;
    }

    @Override
    public BigDecimal findByType(String type, Integer projectId, Long mId, String monthTime, Boolean isPrev) {
        return null;
    }

    @Override
    public BigDecimal findAssembleByType(String subType, String mType, Integer projectId, Long mId, String monthTime, Boolean isPrev) {
        return null;
    }

    @Override
    public BigDecimal findPriceByType(String type, Integer projectId, Long mId, String monthTime, Boolean isPrev) {
        return null;
    }

    @Override
    public BigDecimal buyOrSale(String type, String subType, Long mId, String monthTime, String sumType) {
        return null;
    }

    @Override
    public List<DepotItemStockWarningCount> findStockWarningCount(Integer count, Integer pageSize, Integer projectId) {
        return null;
    }
}
