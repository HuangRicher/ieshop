package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.weberpadmin.client.AppClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Component
public class AppClientHystrix  implements AppClient{
    @Override
    public JSONObject findAppByUserId(String userId) {
        return null;
    }

    @Override
    public JSONObject findDesk() {
        return null;
    }

    @Override
    public JSONArray findRoleAPP(String type, String keyId, String loginName) {
        return null;
    }

    @Override
    public BaseResponseInfo uploadImg(MultipartFile fileInfo, String fileName) {
        return null;
    }

    @Override
    public Object batchDeleteAppByIds(String ids) {
        return null;
    }
}
