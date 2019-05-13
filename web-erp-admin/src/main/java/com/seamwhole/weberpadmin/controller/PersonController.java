package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.PersonClient;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/person")
public class PersonController {
    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Resource
    private PersonClient personClient;

    @GetMapping(value = "/getAllList")
    public BaseResponseInfo getAllList()throws Exception {
        BaseResponseInfo res = personClient.getAllList();
        return res;
    }

    /**
     * 根据Id获取经手人信息
     */
    @GetMapping(value = "/getPersonByIds")
    public BaseResponseInfo getPersonByIds(@RequestParam("personIDs") String personIDs)throws Exception {
        BaseResponseInfo res = personClient.getPersonByIds(personIDs);
        return res;
    }

    /**
     * 根据类型获取经手人信息
     */
    @GetMapping(value = "/getPersonByType")
    public BaseResponseInfo getPersonByType(@RequestParam("type") String type)throws Exception {
        BaseResponseInfo res = personClient.getPersonByType(type);
        return res;
    }

    /**
     * 根据类型获取经手人信息 1-业务员，2-仓管员，3-财务员
     */
    @PostMapping(value = "/getPersonByNumType")
    public JSONArray getPersonByNumType(@RequestParam("type") String typeNum)throws Exception {
        JSONArray dataArray = personClient.getPersonByNumType(typeNum);
        return dataArray;
    }


    /**
     *  批量删除经手人信息
     */
    @RequestMapping(value = "/batchDeletePersonByIds")
    public Object batchDeletePersonByIds(@RequestParam("ids") String ids,
                                         @RequestParam(value="deleteType", required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)
                                                 String deleteType) throws Exception {

        return personClient.batchDeletePersonByIds(ids,deleteType);
    }
}
