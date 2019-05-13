package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.UserClient;
import com.seamwhole.weberpadmin.constants.Constants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${mybatis-plus.status}")
    private String mybatisPlusStatus;

    @Value("${manage.ip}")
    private String manageIp;

    @Value("${manage.port}")
    private Integer managePort;

    @Value("${manage.roleId}")
    private Integer manageRoleId;

    @Resource
    private UserClient userClient;

    private static String message = "成功";
    private static final String HTTP = "http://";
    private static final String CODE_OK = "200";

    @PostMapping(value = "/login")
    public BaseResponseInfo login(@RequestParam(value = "loginame", required = false) String loginame,
                                  @RequestParam(value = "password", required = false) String password,
                                  HttpServletRequest request)throws Exception {
        logger.info("============用户登录 login 方法调用开始==============");

        BaseResponseInfo res =new BaseResponseInfo();
        return res;
    }

    @GetMapping(value = "/getUserSession")
    public BaseResponseInfo getSessionUser(HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        return res;
    }

    @GetMapping(value = "/logout")
    public BaseResponseInfo logout(HttpServletRequest request, HttpServletResponse response)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        return res;
    }

    @PostMapping(value = "/resetPwd")
    public String resetPwd(@RequestParam("id") Long id) throws Exception {
        return userClient.resetPwd(id);
    }

    @PostMapping(value = "/updatePwd")
    public String updatePwd(@RequestParam("userId") Long userId,
                            @RequestParam("password") String password,
                            @RequestParam("oldpwd") String oldpwd)throws Exception {

        return userClient.updatePwd(userId,password,oldpwd);
    }

    /**
     * 获取全部用户数据列表
     */
    @GetMapping(value = "/getAllList")
    public BaseResponseInfo getAllList()throws Exception {
        BaseResponseInfo res = userClient.getAllList();
        return res;
    }

    /**
     *  查询分页用户列表
     */
    @GetMapping(value = "/getUserList")
    public String getUserList(@RequestParam(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                              @RequestParam(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                              @RequestParam(value = Constants.SEARCH, required = false) String search)throws Exception {

        return userClient.getUserList(pageSize,currentPage,search);
    }


    /**
     *  新增用户及机构和用户关系
     */
    @PostMapping("/addUser")
    @ResponseBody
    public Object addUser(@RequestParam("info") String beanJson)throws Exception{
        return userClient.addUser(beanJson);
    }


    /**
     * 注册用户
     */
    @PostMapping(value = "/registerUser")
    public Object registerUser(@RequestParam(value = "loginame", required = false) String loginame,
                               @RequestParam(value = "password", required = false) String password)throws Exception{

        return userClient.registerUser(loginame,password);
    }


    /**
     *  修改用户及机构和用户关系
     */
    @PostMapping("/updateUser")
    public Object updateUser(@RequestParam("info") String beanJson, @RequestParam("id") Long id)throws Exception{

        return userClient.updateUser(beanJson,id);
    }


    @PostMapping("/deleteUser")
    public Object deleteUser(@RequestParam("ids") String ids)throws Exception{

        return userClient.deleteUser(ids);
    }

    @PostMapping("/batchDeleteUser")
    public Object batchDeleteUser(@RequestParam("ids") String ids)throws Exception{

        return userClient.batchDeleteUser(ids);
    }


    @RequestMapping("/getOrganizationUserTree")
    public JSONArray getOrganizationUserTree()throws Exception{
        JSONArray arr=userClient.getOrganizationUserTree();
        return arr;
    }

    @GetMapping("/getTenantStatus")
    public BaseResponseInfo getTenantStatus()throws Exception {
        BaseResponseInfo res = userClient.getTenantStatus();
        return res;
    }
}
