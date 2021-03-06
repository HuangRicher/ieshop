package com.seamwhole.serviceerpcore.controller;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.service.CommonQueryManager;
import com.seamwhole.serviceerpcore.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.seamwhole.serviceerpcore.utils.ResponseJsonUtil.returnJson;


@RestController
public class ResourceController {
    private Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Resource
    private CommonQueryManager configResourceManager;

    @GetMapping(value = "/test/heart")
    public JSONObject exitHeart()throws Exception {
        return JsonUtils.ok();
    }

    @GetMapping(value = "/{apiName}/list/{pageSize}/{currentPage}/{search}")
    public String getList(@PathVariable("apiName") String apiName,
                        @PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                        @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                        @PathVariable(value = Constants.SEARCH, required = false) String search)throws Exception {
        Map<String, String> parameterMap = new HashMap<>();//ParamUtils.requestToMap(request);
        if(pageSize!=null){
            parameterMap.put(Constants.PAGE_SIZE,pageSize+"");
        }
        if(currentPage!=null){
            parameterMap.put(Constants.CURRENT_PAGE,currentPage+"");
        }
        if(StringUtils.isNoneBlank(search)){
            parameterMap.put(Constants.SEARCH, search);
        }
        PageQueryInfo queryInfo = new PageQueryInfo();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        if (pageSize != null && pageSize <= 0) {
            pageSize = 10;
        }
        String offset = ParamUtils.getPageOffset(currentPage, pageSize);
        if (StringUtil.isNotEmpty(offset)) {
            parameterMap.put(Constants.OFFSET, offset);
        }
        List<?> list = configResourceManager.select(apiName, parameterMap);
        objectMap.put("page", queryInfo);
        if (list == null) {
            queryInfo.setRows(new ArrayList<Object>());
            queryInfo.setTotal(BusinessConstants.DEFAULT_LIST_NULL_NUMBER);
            return returnJson(objectMap, "查找不到数据", ErpInfo.OK.code);
        }
        queryInfo.setRows(list);
        queryInfo.setTotal(configResourceManager.counts(apiName, parameterMap));
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }


    @GetMapping(value = "/{apiName}/list/{pageSize}/{currentPage}")
    public String getListData(@PathVariable("apiName") String apiName,
                          @PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                          @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage)throws Exception {
        Map<String, String> parameterMap = new HashMap<>();//ParamUtils.requestToMap(request);
        if(pageSize!=null){
            parameterMap.put(Constants.PAGE_SIZE,pageSize+"");
        }
        if(currentPage!=null){
            parameterMap.put(Constants.CURRENT_PAGE,currentPage+"");
        }
        PageQueryInfo queryInfo = new PageQueryInfo();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        if (pageSize != null && pageSize <= 0) {
            pageSize = 10;
        }
        String offset = ParamUtils.getPageOffset(currentPage, pageSize);
        if (StringUtil.isNotEmpty(offset)) {
            parameterMap.put(Constants.OFFSET, offset);
        }
        List<?> list = configResourceManager.select(apiName, parameterMap);
        objectMap.put("page", queryInfo);
        if (list == null) {
            queryInfo.setRows(new ArrayList<Object>());
            queryInfo.setTotal(BusinessConstants.DEFAULT_LIST_NULL_NUMBER);
            return returnJson(objectMap, "查找不到数据", ErpInfo.OK.code);
        }
        queryInfo.setRows(list);
        queryInfo.setTotal(configResourceManager.counts(apiName, parameterMap));
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }

    @PostMapping(value = "/{apiName}/add")
    public String addResource(@PathVariable("apiName") String apiName,
                              @RequestParam("info") String beanJson,
                              HttpServletRequest request)throws Exception {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int insert = configResourceManager.insert(apiName, beanJson, request);
        if(insert > 0) {
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    @PostMapping(value = "/{apiName}/update")
    public String updateResource(@PathVariable("apiName") String apiName,
                                 @RequestParam("info") String beanJson,
                                 @RequestParam("id") Long id,
                                 HttpServletRequest request)throws Exception {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int update = configResourceManager.update(apiName, beanJson, id, request);
        if(update > 0) {
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    @PostMapping(value = "/{apiName}/{id}/delete")
    public String deleteResource(@PathVariable("apiName") String apiName,
                                 @PathVariable("id") Long id,
                                 HttpServletRequest request)throws Exception {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int delete = configResourceManager.delete(apiName, id, request);
        if(delete > 0) {
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    @PostMapping(value = "/{apiName}/batchDelete")
    public String batchDeleteResource(@PathVariable("apiName") String apiName,
                                      @RequestParam("ids") String ids,
                                      HttpServletRequest request)throws Exception {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int delete = configResourceManager.batchDelete(apiName, ids, request);
        if(delete > 0) {
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    @GetMapping(value = "/{apiName}/checkIsNameExist")
    public String checkIsNameExist(@PathVariable("apiName") String apiName,
                                   @RequestParam("id") Long id,
                                   @RequestParam(value ="name", required = false) String name)throws Exception {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int exist = configResourceManager.checkIsNameExist(apiName, id, name);
        if(exist > 0) {
            objectMap.put("status", true);
        } else {
            objectMap.put("status", false);
        }
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }


}
