package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.OrganizationClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class OrganizationClientHystrix implements OrganizationClient{
    @Override
    public BaseResponseInfo findById(Long id) {
        return null;
    }

    @Override
    public JSONArray getOrganizationTree(Long id) {
        return null;
    }

    @Override
    public Object addOrganization(String beanJson) {
        return null;
    }

    @Override
    public Object editOrganization(String beanJson) {
        return null;
    }

    @Override
    public Object batchDeleteOrganization(String ids) {
        return null;
    }
}
