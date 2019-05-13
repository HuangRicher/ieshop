package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.DepotClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DepotClientHystrix implements DepotClient{
    @Override
    public BaseResponseInfo getAllList() {
        return null;
    }

    @Override
    public JSONArray findUserDepot(String type, String keyId) {
        return null;
    }

    @Override
    public JSONArray findDepotByUserId(String type, String keyId) {
        return null;
    }

    @Override
    public String getDepotList(Integer pageSize, Integer currentPage, String search) {
        return null;
    }

    @Override
    public Object batchDeleteDepotByIds(String ids, String deleteType) {
        return null;
    }

    @Override
    public String updateDepotIsDefault(Boolean isDefault, Long depotID) {
        return null;
    }
}
