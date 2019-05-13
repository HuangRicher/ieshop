package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.weberpadmin.client.AppClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/app")
public class AppController {
    private Logger logger = LoggerFactory.getLogger(AppController.class);

    @Value("${mybatis-plus.status}")
    private String mybatisPlusStatus;

    @Autowired
    private AppClient appClient;


    /**
     * 根据用户查询有权限的app
     */
    @GetMapping(value = "/findAppByUserId")
    public JSONObject findAppByUserId(@RequestParam("userId") String userId)throws Exception {

        return  appClient.findAppByUserId(userId);
    }

    @GetMapping(value = "/findDesk")
    public JSONObject findDesk()throws Exception {
        JSONObject obj = appClient.findDesk();
        return obj;
    }

    /**
     * 角色对应应用显示
     * @return
     */
    @PostMapping(value = "/findRoleAPP")
    public JSONArray findRoleAPP(@RequestParam("UBType") String type, @RequestParam("UBKeyId") String keyId)throws Exception {
        String loginName="";
        JSONArray arr = appClient.findRoleAPP(type,keyId,loginName);
        return arr;
    }

    /**
     * 上传图片
     * @param fileInfo
     */
    @PostMapping(value = "/uploadImg")
    public BaseResponseInfo uploadImg(@RequestParam("file") MultipartFile fileInfo)throws Exception {
        BaseResponseInfo res = appClient.uploadImg(fileInfo,fileInfo.getOriginalFilename());
        return res;
    }

    /**
     *  批量删除应用信息
     * @Param: ids
     */
    @RequestMapping(value = "/batchDeleteAppByIds")
    public Object batchDeleteAppByIds(@RequestParam("ids") String ids) throws Exception {

        return appClient.batchDeleteAppByIds(ids);
    }
}
