package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.weberpadmin.client.ResourceClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ResourceClientHystrix implements ResourceClient{
    @Override
    public JSONObject exitHeart() {
        return null;
    }

    @Override
    public String getList(String apiName, Integer pageSize, Integer currentPage, String search) {
        return null;
    }

    @Override
    public String addResource(String apiName, String beanJson) {
        return null;
    }

    @Override
    public String updateResource(String apiName, String beanJson, Long id) {
        return null;
    }

    @Override
    public String deleteResource(String apiName, Long id) {
        return null;
    }

    @Override
    public String batchDeleteResource(String apiName, String ids) {
        return null;
    }

    @Override
    public String checkIsNameExist(String apiName, Long id, String name) {
        return null;
    }
}
