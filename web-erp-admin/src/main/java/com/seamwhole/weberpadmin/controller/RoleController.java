package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.RoleClient;
import com.seamwhole.weberpadmin.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleClient roleClient;


    /**
     * 角色对应应用显示
     */
    @PostMapping(value = "/findUserRole")
    public JSONArray findUserRole(@RequestParam("UBType") String type,
                                  @RequestParam("UBKeyId") String keyId)throws Exception {
        JSONArray arr = roleClient.findUserRole(type,keyId);
        return arr;
    }

    @PostMapping(value = "/list")
    public List<Role> list() throws Exception {
        return roleClient.list();
    }

    /**
     *  逻辑删除角色信息
     */
    @RequestMapping(value = "/batchDeleteRoleByIds")
    public Object batchDeleteRoleByIds(@RequestParam("ids") String ids) throws Exception {

        return roleClient.batchDeleteRoleByIds(ids);
    }

}
