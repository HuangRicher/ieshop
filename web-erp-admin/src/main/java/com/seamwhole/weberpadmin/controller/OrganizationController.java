package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.OrganizationClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/organization")
public class OrganizationController {
    private Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Resource
    private OrganizationClient organizationClient;


    /**
     * 根据id来查询机构信息
     */
    @RequestMapping(value = "/findById")
    public BaseResponseInfo findById(@RequestParam("id") Long id) throws Exception {
        BaseResponseInfo res = organizationClient.findById(id);
        return res;
    }

    /**
     * 获取机构树数据
     */
    @RequestMapping(value = "/getOrganizationTree")
    public JSONArray getOrganizationTree(@RequestParam("id") Long id) throws Exception{
       JSONArray arr=organizationClient.getOrganizationTree(id);
        return arr;
    }


    /**
     *  新增机构信息
     */
    @RequestMapping(value = "/addOrganization")
    public Object addOrganization(@RequestParam("info") String beanJson) throws Exception {

        return organizationClient.addOrganization(beanJson);
    }


    /**
     *  修改机构信息
     */
    @RequestMapping(value = "/editOrganization")
    public Object editOrganization(@RequestParam("info") String beanJson) throws Exception {

        return organizationClient.editOrganization(beanJson);
    }


    /**
     *  批量删除机构信息
     */
    @RequestMapping(value = "/batchDeleteOrganization")
    public Object batchDeleteOrganization(@RequestParam("ids") String ids) throws Exception {

        return organizationClient.batchDeleteOrganization(ids);
    }
}
