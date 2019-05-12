package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.RoleClient;
import com.seamwhole.weberpadmin.domain.Role;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class RoleClientHystrix implements RoleClient{
    @Override
    public JSONArray findUserRole(String type, String keyId, HttpServletRequest request) {
        return null;
    }

    @Override
    public List<Role> list(HttpServletRequest request) {
        return null;
    }

    @Override
    public Object batchDeleteRoleByIds(String ids) {
        return null;
    }
}
