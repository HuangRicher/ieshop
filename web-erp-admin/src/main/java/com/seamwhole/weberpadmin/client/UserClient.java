package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.hystrix.UserClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.Constants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = UserClientHystrix.class)
public interface UserClient {

    @PostMapping(value = "/user/login")
    BaseResponseInfo login(@RequestParam("loginame") String loginame,
                           @RequestParam("password") String password);


    @PostMapping(value = "/user/resetPwd")
    String resetPwd(@RequestParam("id") Long id) ;



    @PostMapping(value = "/user/updatePwd/{userId}/{password}/{oldpwd}")
    String updatePwd(@PathVariable("userId") Long userId,
                     @PathVariable("password") String password,
                     @PathVariable("oldpwd") String oldpwd);


    /**
     * 获取全部用户数据列表
     */
    @GetMapping(value = "/user/getAllList")
     BaseResponseInfo getAllList();


    /**
     *  查询分页用户列表
     */
    @GetMapping(value = "/user/getUserList/{pageSize}/{currentPage}/{search}")
    String getUserList(@PathVariable(value = Constants.PAGE_SIZE) Integer pageSize,
                       @PathVariable(value = Constants.CURRENT_PAGE) Integer currentPage,
                       @PathVariable(value = Constants.SEARCH) String search);

    /**
     *  新增用户及机构和用户关系
     */
    @PostMapping("/user/addUser")
    Object addUser(@RequestParam("info") String beanJson);


    /**
     * 注册用户
     */
    @PostMapping(value = "/user/registerUser")
    Object registerUser(@RequestParam("loginame") String loginame,
                        @RequestParam("password") String password);


    /**
     *  修改用户及机构和用户关系
     */
    @PostMapping("/user/updateUser")
    Object updateUser(@RequestParam("info") String beanJson,
                      @RequestParam("id") Long id);


    @PostMapping("/user/deleteUser")
    Object deleteUser(@RequestParam("ids") String ids);


    @PostMapping("/user/batchDeleteUser")
    Object batchDeleteUser(@RequestParam("ids") String ids);



    @RequestMapping("/user/getOrganizationUserTree")
    JSONArray getOrganizationUserTree();

    @GetMapping("/user/getTenantStatus")
    BaseResponseInfo getTenantStatus();

    @GetMapping("/user/getUserByUserName")
    User getUserByUserName(@RequestParam("username") String username);

    @GetMapping("/user/validateUser")
    Integer validateUser(@RequestParam("username") String username, @RequestParam("password") String password);
}
