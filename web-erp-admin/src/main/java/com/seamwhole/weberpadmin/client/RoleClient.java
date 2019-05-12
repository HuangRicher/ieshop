package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.hystrix.RoleClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.domain.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = RoleClientHystrix.class)
public interface RoleClient {


    /**
     * 角色对应应用显示
     */
    @PostMapping(value = "/role/findUserRole")
    JSONArray findUserRole(@RequestParam("UBType") String type,
                           @RequestParam("UBKeyId") String keyId);

    @PostMapping(value = "/role/list")
    List<Role> list();

    /**
     *  逻辑删除角色信息
     */
    @PostMapping(value = "/role/batchDeleteRoleByIds")
    Object batchDeleteRoleByIds(@RequestParam("ids") String ids);

}
