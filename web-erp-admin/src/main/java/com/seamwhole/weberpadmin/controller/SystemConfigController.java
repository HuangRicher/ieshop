package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.SystemConfigClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/systemConfig")
public class SystemConfigController {
    private Logger logger = LoggerFactory.getLogger(SystemConfigController.class);
    @Resource
    private SystemConfigClient systemConfigClient;


    /**
     *  批量删除系统配置信息
     */
    @RequestMapping(value = "/batchDeleteSystemConfigByIds")
    public Object batchDeleteSystemConfigByIds(@RequestParam("ids") String ids) throws Exception {

        return systemConfigClient.batchDeleteSystemConfigByIds(ids);
    }

}
