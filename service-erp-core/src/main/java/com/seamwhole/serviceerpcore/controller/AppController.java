package com.seamwhole.serviceerpcore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.model.App;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.model.UserBusiness;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.service.AppService;
import com.seamwhole.serviceerpcore.service.UserBusinessService;
import com.seamwhole.serviceerpcore.utils.BaseResponseInfo;
import com.seamwhole.serviceerpcore.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@RestController
@RequestMapping(value = "/app")
public class AppController {
    private Logger logger = LoggerFactory.getLogger(AppController.class);

    @Value("${mybatis-plus.status}")
    private String mybatisPlusStatus;

    @Resource
    private AppService appService;

    @Resource
    private UserBusinessService userBusinessService;

    /**
     * 根据用户查询有权限的app
     * @param userId
     * @return
     */
    @GetMapping(value = "/findAppByUserId")
    public JSONObject findAppByUserId(@RequestParam("userId") String userId)throws Exception {
        List<UserBusiness> roleList = userBusinessService.findRoleByUserId(userId);
        String roles = null;
        if(roleList!=null && roleList.size()>0 && roleList.get(0)!=null){
            roles = roleList.get(0).getValue();
        }
        if(roles!=null) {
            roles = roles.replaceAll("\\]\\[",",").replaceAll("\\]","").replaceAll("\\[",""); //转为逗号隔开的
        }
        List<UserBusiness> appList = userBusinessService.findAppByRoles(roles);
        String apps = null;
        if(appList!=null && appList.size()>0 && appList.get(0)!=null){
            apps = appList.get(0).getValue();
        }
        if(apps!=null) {
            apps = apps.replaceAll("\\]\\[",",").replaceAll("\\]","").replaceAll("\\[",""); //转为逗号隔开的
        }
        JSONObject obj = new JSONObject();
        List<App> dockList = appService.findAppInIds(apps,"dock");
        JSONArray dockArray = new JSONArray();
        if (null != dockList) {
            for (App app : dockList) {
                JSONObject item = new JSONObject();
                item.put("id", app.getId());
                item.put("title", app.getName());
                item.put("type", app.getType());
                item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                item.put("url", app.getUrl());
                item.put("width", app.getWidth());
                item.put("height", app.getHeight());
                item.put("isresize", app.getResize());
                item.put("isopenmax", app.getOpenmax());
                item.put("isflash", app.getFlash());
                dockArray.add(item);
            }
        }
        obj.put("dock",dockArray);

        List<App> deskList = appService.findAppInIds(apps,"desk");
        JSONArray deskArray = new JSONArray();
        if (null != deskList) {
            for (App app : deskList) {
                JSONObject item = new JSONObject();
                item.put("id", app.getId());
                item.put("title", app.getName());
                item.put("type", app.getType());
                item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                item.put("url", "/common/menu?appID=" + app.getNumber() + "&id=" + app.getId());
                item.put("width", app.getWidth());
                item.put("height", app.getHeight());
                item.put("isresize", app.getResize());
                item.put("isopenmax", app.getOpenmax());
                item.put("isflash", app.getFlash());
                deskArray.add(item);
            }
        }
        obj.put("desk",deskArray);
        return obj;
    }

    @GetMapping(value = "/findDesk")
    public JSONObject findDesk()throws Exception {
        JSONObject obj = new JSONObject();
        List<App> dockList = appService.findDock();
        JSONArray dockArray = new JSONArray();
        if (null != dockList) {
            for (App app : dockList) {
                JSONObject item = new JSONObject();
                item.put("id", app.getId());
                item.put("title", app.getName());
                item.put("type", app.getType());
                item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                item.put("url", app.getUrl());
                item.put("width", app.getWidth());
                item.put("height", app.getHeight());
                item.put("isresize", app.getResize());
                item.put("isopenmax", app.getOpenmax());
                item.put("isflash", app.getFlash());
                dockArray.add(item);
            }
        }
        obj.put("dock",dockArray);

        List<App> deskList = appService.findDesk();
        JSONArray deskArray = new JSONArray();
        if (null != deskList) {
            for (App app : deskList) {
                JSONObject item = new JSONObject();
                item.put("id", app.getId());
                item.put("title", app.getName());
                item.put("type", app.getType());
                item.put("icon", "../../upload/images/deskIcon/" + app.getIcon());
                item.put("url", "../../pages/common/menu.html?appID=" + app.getNumber() + "&id=" + app.getId());
                item.put("width", app.getWidth());
                item.put("height", app.getHeight());
                item.put("isresize", app.getResize());
                item.put("isopenmax", app.getOpenmax());
                item.put("isflash", app.getFlash());
                deskArray.add(item);
            }
        }
        obj.put("desk",deskArray);
        return obj;
    }

    /**
     * 角色对应应用显示
     * @return
     */
    @PostMapping(value = "/findRoleAPP/{type}/{keyId}/{loginName}")
    public JSONArray findRoleAPP(@PathVariable("type") String type, @PathVariable("keyId") String keyId,
                                 @PathVariable("loginName")String loginName)throws Exception {
        JSONArray arr = new JSONArray();
        try {
            List<App> dataListApp = appService.findRoleAPP();
            //开始拼接json数据
            JSONObject outer = new JSONObject();
            outer.put("id", 1);
            outer.put("text", "应用列表");
            outer.put("state", "open");
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if (null != dataListApp) {
                //根据条件从列表里面移除"系统管理"
                List<App> dataList = new ArrayList<App>();
                for (App appOne : dataListApp) {
                    if(("open").equals(mybatisPlusStatus)){
                        if(("admin").equals(loginName)) {
                            dataList.add(appOne);
                        } else {
                            if(!("系统管理").equals(appOne.getName())) {
                                dataList.add(appOne);
                            }
                        }
                    } else {
                        dataList.add(appOne);
                    }
                }

                //筛选应用列表
                for (App app : dataList) {
                    JSONObject item = new JSONObject();
                    item.put("id", app.getId());
                    item.put("text", app.getName());
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist(type, keyId, "[" + app.getId().toString() + "]");
                    } catch (Exception e) {
                        logger.error(">>>>>>>>>>>>>>>>>设置角色对应的应用：类型" + type + " KeyId为： " + keyId + " 存在异常！");
                    }
                    if (flag == true) {
                        item.put("checked", true);
                    }
                    //结束
                    dataArray.add(item);
                }
            }
            outer.put("children", dataArray);
            arr.add(outer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * 上传图片
     * @param fileInfo
     */
    @PostMapping(value = "/uploadImg")
    public BaseResponseInfo uploadImg(MultipartFile fileInfo, @RequestParam("fileInfoName") String fileName)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            if (fileInfo != null) {
                String path = "D:/upload/images/deskIcon/"; //windows环境下的路径
                Properties pro = System.getProperties();
                String osName = pro.getProperty("os.name");//获得当前操作系统的名称
                if("Linux".equals(osName) || "linux".equals(osName) || "LINUX".equals(osName)){
                    path =  "/mnt/upload/images/deskIcon/"; //linux环境下的路径
                }
                FileUtils.SaveFileFromInputStream(fileInfo.getInputStream(), path, fileName);
                res.code = 200;
                res.data = "上传图片成功";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            res.code = 500;
            res.data = "获取图片失败";
        } catch (IOException e) {
            e.printStackTrace();
            res.code = 500;
            res.data = "上传图片失败";
        }
        return res;
    }
    /**
     *  批量删除应用信息
     * @Param: ids
     * @return java.lang.Object
     */
    @PostMapping(value = "/batchDeleteAppByIds")
    public Object batchDeleteAppByIds(@RequestParam("ids") String ids) throws Exception {

        JSONObject result = ExceptionConstants.standardSuccess();
        int i= appService.batchDeleteAppByIds(ids);
        if(i<1){
            logger.error("异常码[{}],异常提示[{}],参数,ids[{}]",
                    ExceptionConstants.APP_DELETE_FAILED_CODE,ExceptionConstants.APP_DELETE_FAILED_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.APP_DELETE_FAILED_CODE,
                    ExceptionConstants.APP_DELETE_FAILED_MSG);
        }
        return result;
    }
}
