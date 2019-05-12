package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.weberpadmin.client.ResourceClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ResourceClientHystrix implements ResourceClient{
    @Override
    public JSONObject exitHeart(HttpServletRequest request) {
        return null;
    }

    @Override
    public String getList(String apiName, Integer pageSize, Integer currentPage, String search, HttpServletRequest request) {
        return null;
    }

    @Override
    public String addResource(String apiName, String beanJson, HttpServletRequest request) {
        return null;
    }

    @Override
    public String updateResource(String apiName, String beanJson, Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String deleteResource(String apiName, Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String batchDeleteResource(String apiName, String ids, HttpServletRequest request) {
        return null;
    }

    @Override
    public String checkIsNameExist(String apiName, Long id, String name, HttpServletRequest request) {
        return null;
    }
}
