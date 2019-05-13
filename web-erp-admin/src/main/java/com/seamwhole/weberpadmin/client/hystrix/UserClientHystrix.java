package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.UserClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserClientHystrix implements UserClient {
    @Override
    public BaseResponseInfo login(String loginame, String password) {
        return null;
    }

    @Override
    public String resetPwd(Long id) {
        return null;
    }

    @Override
    public String updatePwd(Long userId, String password, String oldpwd) {
        return null;
    }

    @Override
    public BaseResponseInfo getAllList() {
        return null;
    }

    @Override
    public String getUserList(Integer pageSize, Integer currentPage, String search) {
        return null;
    }

    @Override
    public Object addUser(String beanJson) {
        return null;
    }

    @Override
    public Object registerUser(String loginame, String password) {
        return null;
    }

    @Override
    public Object updateUser(String beanJson, Long id) {
        return null;
    }

    @Override
    public Object deleteUser(String ids) {
        return null;
    }

    @Override
    public Object batchDeleteUser(String ids) {
        return null;
    }

    @Override
    public JSONArray getOrganizationUserTree() {
        return null;
    }

    @Override
    public BaseResponseInfo getTenantStatus() {
        return null;
    }
}
