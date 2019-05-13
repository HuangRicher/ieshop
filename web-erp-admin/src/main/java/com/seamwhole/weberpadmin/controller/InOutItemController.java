package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.InOutItemClient;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/inOutItem")
public class InOutItemController {
    private Logger logger = LoggerFactory.getLogger(InOutItemController.class);

    @Resource
    private InOutItemClient inOutItemClient;


    /**
     * 查找收支项目信息-下拉框
     */
    @GetMapping(value = "/findBySelect")
    public String findBySelect(@RequestParam("type") String type) throws Exception{
        String res = inOutItemClient.findBySelect(type);
        return res;
    }


    /**
     *  批量删除收支项目信息
     */
    @RequestMapping(value = "/batchDeleteInOutItemByIds")
    public Object batchDeleteInOutItemByIds(@RequestParam("ids") String ids,
                                            @RequestParam(value="deleteType", required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)
                                                    String deleteType) throws Exception {


        return inOutItemClient.batchDeleteInOutItemByIds(ids,deleteType);
    }

}
