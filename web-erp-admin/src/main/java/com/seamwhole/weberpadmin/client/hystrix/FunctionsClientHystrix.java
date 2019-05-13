package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.FunctionsClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FunctionsClientHystrix implements FunctionsClient{
    @Override
    public JSONArray findMenu(String pNumber, String hasFunctions) {
        return null;
    }

    @Override
    public JSONArray findRoleFunctions(String type, String keyId, String loginName) {
        return null;
    }

    @Override
    public BaseResponseInfo findByIds(String functionsIds) {
        return null;
    }

    @Override
    public Object batchDeleteFunctionsByIds(String ids) {
        return null;
    }
}
