package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.FunctionsClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/functions")
public class FunctionsController {
    private Logger logger = LoggerFactory.getLogger(FunctionsController.class);

    @Value("${mybatis-plus.status}")
    private String mybatisPlusStatus;

    @Autowired
    private FunctionsClient functionsClient;


    @PostMapping(value = "/findMenu")
    public JSONArray findMenu(@RequestParam(value="pNumber") String pNumber,
                            @RequestParam(value="hasFunctions") String hasFunctions)throws Exception {
        //存放数据json数组
        JSONArray dataArray = functionsClient.findMenu(pNumber,hasFunctions);
        return dataArray;
    }


    /**
     * 角色对应功能显示
     */
    @PostMapping(value = "/findRoleFunctions")
    public JSONArray findRoleFunctions(@RequestParam("UBType") String type,
                                       @RequestParam("UBKeyId") String keyId)throws Exception {

        JSONArray arr = functionsClient.findRoleFunctions(type,keyId,"");
        return arr;
    }

    /**
     * 根据id列表查找功能信息
     */
    @GetMapping(value = "/findByIds")
    public BaseResponseInfo findByIds(@RequestParam("functionsIds") String functionsIds)throws Exception {

        BaseResponseInfo res = functionsClient.findByIds(functionsIds);
        return res;
    }


    /**
     *  批量删除功能模块信息
     */
    @RequestMapping(value = "/batchDeleteFunctionsByIds")
    public Object batchDeleteFunctionsByIds(@RequestParam("ids") String ids) throws Exception {

        return functionsClient.batchDeleteFunctionsByIds(ids);
    }
}
