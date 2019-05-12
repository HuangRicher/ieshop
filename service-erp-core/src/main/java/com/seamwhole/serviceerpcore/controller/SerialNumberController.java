package com.seamwhole.serviceerpcore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.mapper.vo.SerialNumberEx;
import com.seamwhole.serviceerpcore.exception.BusinessParamCheckingException;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.service.SerialNumberService;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
public class SerialNumberController {
    private Logger logger = LoggerFactory.getLogger(SerialNumberController.class);

    @Resource
    private SerialNumberService serialNumberService;

    /**
     *  检查序列号是否存在
     * @Param: id
     * @Param: materialName
     * @Param: serialNumber
     * @Param: request
     * @return java.lang.Object
     */
    @PostMapping("/serialNumber/checkIsExist/{id}/{materialName}/{serialNumber}")
    public Object checkIsExist(@PathVariable("id") Long id,
                               @PathVariable("materialName") String materialName,
                               @PathVariable("serialNumber") String serialNumber) throws Exception{
        JSONObject result = ExceptionConstants.standardSuccess();
        if(StringUtil.isEmpty(serialNumber)){
            throw new BusinessParamCheckingException(ExceptionConstants.SERIAL_NUMBERE_NOT_BE_EMPTY_CODE,
                    ExceptionConstants.SERIAL_NUMBERE_NOT_BE_EMPTY_MSG);
        }
        serialNumberService.checkIsExist(id, materialName, serialNumber);
        return result;
    }


    /**
     *  新增序列号信息
     * @Param: beanJson
     * @Param: request
     * @return java.lang.Object
     */
    @PostMapping("/serialNumber/addSerialNumber")
    public Object addSerialNumber(@RequestParam("info") String beanJson)throws Exception{
        JSONObject result = ExceptionConstants.standardSuccess();
        SerialNumberEx sne= JSON.parseObject(beanJson, SerialNumberEx.class);
        serialNumberService.addSerialNumber(sne);
        return result;

    }

    /**
     *  修改序列号信息
     * @Param: beanJson
     * @return java.lang.Object
     */
    @PostMapping("/serialNumber/updateSerialNumber")
    public Object updateSerialNumber(@RequestParam("info") String beanJson)throws Exception{

        JSONObject result = ExceptionConstants.standardSuccess();
        SerialNumberEx sne= JSON.parseObject(beanJson, SerialNumberEx.class);
        serialNumberService.updateSerialNumber(sne);
        return result;

    }


    /**
     *批量添加序列号
     * @Param: materialName
     * @Param: serialNumberPrefix
     * @Param: batAddTotal
     * @Param: remark
     * @return java.lang.Object
     */
    @PostMapping("/serialNumber/batAddSerialNumber/{materialName}/{serialNumberPrefix}/{batAddTotal}/{remark}")
    public Object batAddSerialNumber(@PathVariable("materialName") String materialName,
                                     @PathVariable("serialNumberPrefix") String serialNumberPrefix,
                                     @PathVariable("batAddTotal") Integer batAddTotal,
                                     @PathVariable("remark") String remark)throws Exception{

        JSONObject result = ExceptionConstants.standardSuccess();
        serialNumberService.batAddSerialNumber(materialName,serialNumberPrefix,batAddTotal,remark);
        return result;

    }

    /**
     *  逻辑删除序列号信息
     * @Param: ids
     * @return java.lang.Object
     */
    @PostMapping(value = "/serialNumber/batchDeleteSerialNumberByIds")
    public Object batchDeleteSerialNumberByIds(@RequestParam("ids") String ids) throws Exception {
        JSONObject result = ExceptionConstants.standardSuccess();
        int i= serialNumberService.batchDeleteSerialNumberByIds(ids);
        if(i<1){
            logger.error("异常码[{}],异常提示[{}],参数,ids[{}]",
                    ExceptionConstants.SERIAL_NUMBERE_DELETE_FAILED_CODE,ExceptionConstants.SERIAL_NUMBERE_DELETE_FAILED_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.SERIAL_NUMBERE_DELETE_FAILED_CODE,
                    ExceptionConstants.SERIAL_NUMBERE_DELETE_FAILED_MSG);
        }
        return result;
    }

}
