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
    public BaseResponseInfo login(String loginame, String password, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo getSessionUser(HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo logout(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String resetPwd(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String updatePwd(Long userId, String password, String oldpwd, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponseInfo getAllList(HttpServletRequest request) {
        return null;
    }

    @Override
    public String getUserList(Integer pageSize, Integer currentPage, String search) {
        return null;
    }

    @Override
    public Object addUser(String beanJson, HttpServletRequest request) {
        return null;
    }

    @Override
    public Object registerUser(String loginame, String password, HttpServletRequest request) {
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
    public BaseResponseInfo getTenantStatus(HttpServletRequest request) {
        return null;
    }
}
