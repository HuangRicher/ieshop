package com.seamwhole.weberpadmin.client.hystrix;

import com.seamwhole.weberpadmin.client.UserBusinessClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserBusinessClientHystrix implements UserBusinessClient{
    @Override
    public BaseResponseInfo getBasicData(String keyId, String type) {
        return null;
    }

    @Override
    public String checkIsValueExist(String type, String keyId) {
        return null;
    }

    @Override
    public BaseResponseInfo updateBtnStr(Long userBusinessId, String btnStr) {
        return null;
    }

    @Override
    public Object batchDeleteUserBusinessByIds(String ids) {
        return null;
    }
}
