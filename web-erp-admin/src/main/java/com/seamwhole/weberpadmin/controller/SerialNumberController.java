package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.weberpadmin.client.SerialNumberClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class SerialNumberController {
    private Logger logger = LoggerFactory.getLogger(SerialNumberController.class);

    @Autowired
    private SerialNumberClient serialNumberClient;


    /**
     * 检查序列号是否存在
     */
    @PostMapping("/serialNumber/checkIsExist")
    @ResponseBody
    public Object checkIsExist(@RequestParam("id") Long id,
                               @RequestParam("materialName") String materialName,
                               @RequestParam("serialNumber") String serialNumber) throws Exception{


        return serialNumberClient.checkIsExist(id,materialName,serialNumber);
    }


    /**
     *  新增序列号信息
     */
    @PostMapping("/serialNumber/addSerialNumber")
    @ResponseBody
    public Object addSerialNumber(@RequestParam("info") String beanJson)throws Exception{

        return serialNumberClient.addSerialNumber(beanJson);

    }


    /**
     *  修改序列号信息
     */
    @PostMapping("/serialNumber/updateSerialNumber")
    @ResponseBody
    public Object updateSerialNumber(@RequestParam("info") String beanJson)throws Exception{


        return serialNumberClient.updateSerialNumber(beanJson);

    }


    /**
     *批量添加序列号
     */
    @PostMapping("/serialNumber/batAddSerialNumber")
    @ResponseBody
    public Object batAddSerialNumber(@RequestParam("materialName") String materialName,
                                     @RequestParam("serialNumberPrefix") String serialNumberPrefix,
                                     @RequestParam("batAddTotal") Integer batAddTotal,
                                     @RequestParam("remark") String remark)throws Exception{


        return serialNumberClient.batAddSerialNumber(materialName,serialNumberPrefix,batAddTotal,remark);

    }


    /**
     * 逻辑删除序列号信息
     */
    @RequestMapping(value = "/serialNumber/batchDeleteSerialNumberByIds")
    public Object batchDeleteSerialNumberByIds(@RequestParam("ids") String ids) throws Exception {

        return serialNumberClient.batchDeleteSerialNumberByIds(ids);
    }

}
