package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.UnitClient;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/unit")
public class UnitController {
    private Logger logger = LoggerFactory.getLogger(UnitController.class);
    @Resource
    private UnitClient unitClient;


    /**
     *  批量删除系统配置信息
     */
    @RequestMapping(value = "/batchDeleteUnitByIds")
    public Object batchDeleteUnitByIds(@RequestParam("ids") String ids,
                                       @RequestParam(value="deleteType", required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)
                                               String deleteType) throws Exception {

        return unitClient.batchDeleteUnitByIds(ids,deleteType);
    }
}
