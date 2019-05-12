package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.UserBusinessClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserBusinessClientHystrix implements UserBusinessClient{
    @Override
    public BaseResponseInfo getBasicData(String keyId, String type, HttpServletRequest request) {
        return null;
    }

    @Override
    public String checkIsValueExist(String type, String keyId, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo updateBtnStr(Long userBusinessId, String btnStr, HttpServletRequest request) {
        return null;
    }

    @Override
    public Object batchDeleteUserBusinessByIds(String ids) {
        return null;
    }
}
