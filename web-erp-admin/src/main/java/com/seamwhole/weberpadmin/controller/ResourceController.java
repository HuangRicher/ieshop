package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.weberpadmin.client.ResourceClient;
import com.seamwhole.weberpadmin.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;



@RestController
public class ResourceController {
    private Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Resource
    private ResourceClient resourceClient;

    @GetMapping(value = "/test/heart")
    public JSONObject exitHeart()throws Exception {
        return resourceClient.exitHeart();
    }

    @GetMapping(value = "/{apiName}/list")
    public String getList(@PathVariable("apiName") String apiName,
                          @RequestParam(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                          @RequestParam(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                          @RequestParam(value = Constants.SEARCH, required = false) String search)throws Exception {

        return resourceClient.getList(apiName,pageSize,currentPage,search);
    }

    @PostMapping(value = "/{apiName}/add")
    public String addResource(@PathVariable("apiName") String apiName,
                              @RequestParam("info") String beanJson)throws Exception {

        return resourceClient.addResource(apiName,beanJson);
    }

    @PostMapping(value = "/{apiName}/update", produces = {"application/javascript", "application/json"})
    public String updateResource(@PathVariable("apiName") String apiName,
                                 @RequestParam("info") String beanJson,
                                 @RequestParam("id") Long id)throws Exception {

        return resourceClient.updateResource(apiName,beanJson,id);
    }


    @PostMapping(value = "/{apiName}/{id}/delete")
    public String deleteResource(@PathVariable("apiName") String apiName,
                                 @PathVariable Long id)throws Exception {

        return resourceClient.deleteResource(apiName,id);
    }


    @PostMapping(value = "/{apiName}/batchDelete")
    public String batchDeleteResource(@PathVariable("apiName") String apiName,
                                      @RequestParam("ids") String ids)throws Exception {

        return resourceClient.batchDeleteResource(apiName,ids);
    }


    @GetMapping(value = "/{apiName}/checkIsNameExist")
    public String checkIsNameExist(@PathVariable("apiName") String apiName,
                                   @RequestParam("id") Long id,
                                   @RequestParam(value ="name", required = false) String name)throws Exception {

        return resourceClient.checkIsNameExist(apiName,id,name);
    }


}
