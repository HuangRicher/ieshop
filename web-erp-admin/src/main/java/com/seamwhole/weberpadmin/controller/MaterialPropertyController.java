package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.MaterialPropertyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/materialProperty")
public class MaterialPropertyController {
    private Logger logger = LoggerFactory.getLogger(MaterialPropertyController.class);
    @Resource
    private MaterialPropertyClient materialPropertyClient;

    /**
     *  批量删除商品扩展信息
     */
    @RequestMapping(value = "/batchDeleteMaterialPropertyByIds")
    public Object batchDeleteMaterialPropertyByIds(@RequestParam("ids") String ids) throws Exception {

        return materialPropertyClient.batchDeleteMaterialPropertyByIds(ids);
    }
}
