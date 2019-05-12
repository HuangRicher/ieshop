package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.PersonClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PersonClientHystrix implements PersonClient{
    @Override
    public BaseResponseInfo getAllList(HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo getPersonByIds(String personIDs, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo getPersonByType(String type, HttpServletRequest request) {
        return null;
    }

    @Override
    public JSONArray getPersonByNumType(String typeNum, HttpServletRequest request) {
        return null;
    }

    @Override
    public Object batchDeletePersonByIds(String ids, String deleteType) {
        return null;
    }
}
