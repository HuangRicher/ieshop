package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.weberpadmin.client.UserClient;
import com.seamwhole.weberpadmin.constants.Constants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.User;
import com.seamwhole.weberpadmin.utils.ExceptionCodeConstants;
import com.seamwhole.weberpadmin.utils.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @Resource
    private UserClient userClient;

    private static String message = "成功";
    private static final String HTTP = "http://";
    private static final String CODE_OK = "200";
    private String mybatisPlusStatus="open";
    private String manageIp="127.0.0.1";
    private Integer managePort=10005;

    @PostMapping(value = "/login")
    public BaseResponseInfo login(@RequestParam(value = "loginame", required = false) String loginame,
                                  @RequestParam(value = "password", required = false) String password,
                                  HttpServletRequest request)throws Exception {
        logger.info("============用户登录 login 方法调用开始==============");
        String msgTip = "";
        User user=null;
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            String username = loginame.trim();
            password = password.trim();
            //判断用户是否已经登录过，登录过不再处理
            Object userInfo = request.getSession().getAttribute("user");
            User sessionUser = new User();
            if (userInfo != null) {
                sessionUser = (User) userInfo;
            }
            if (sessionUser != null && username.equalsIgnoreCase(sessionUser.getLoginame())) {
                logger.info("====用户 " + username + "已经登录过, login 方法调用结束====");
                msgTip = "user already login";
            }
            //获取用户状态
            int userStatus = -1;
            try {
                userStatus = userClient.validateUser(username, password);
            } catch (Exception e) {
                logger.error(">>>>>>>>>>>>>用户  " + username + " 登录 login 方法 访问服务层异常====", e);
                msgTip = "access service exception";
            }
            switch (userStatus) {
                case ExceptionCodeConstants.UserExceptionCode.USER_NOT_EXIST:
                    msgTip = "user is not exist";
                    break;
                case ExceptionCodeConstants.UserExceptionCode.USER_PASSWORD_ERROR:
                    msgTip = "user password error";
                    break;
                case ExceptionCodeConstants.UserExceptionCode.BLACK_USER:
                    msgTip = "user is black";
                    break;
                case ExceptionCodeConstants.UserExceptionCode.USER_ACCESS_EXCEPTION:
                    msgTip = "access service error";
                    break;
                default:
                    try {
                        //验证通过 ，可以登录，放入session，记录登录日志
                        user = userClient.getUserByUserName(username);
                        //                    logService.create(new Logdetails(user, "登录系统", model.getClientIp(),
                        //                            new Timestamp(System.currentTimeMillis()), (short) 0, "管理用户：" + username + " 登录系统", username + " 登录系统"));
                        msgTip = "user can login";
                        request.getSession().setAttribute("user",user);
                        if(("open").equals(mybatisPlusStatus)) {
                            String url = HTTP + manageIp + ":" + managePort + "/tenant/getTenant?tenantId=" + user.getTenantId();
                            JSONObject obj = HttpClient.httpGet(url);
                            if(obj!=null && obj.getString("code").equals(CODE_OK)) {
                                JSONObject dataObj = obj.getJSONObject("data");
                                if(dataObj!=null) {
                                    String tenantId = dataObj.getString("tenantId");
                                    String userNumLimit = dataObj.getString("userNumLimit");
                                    String billsNumLimit = dataObj.getString("billsNumLimit");
                                    if(tenantId!=null) {
                                        request.getSession().setAttribute("tenantId",tenantId); //租户tenantId
                                        request.getSession().setAttribute("userNumLimit",userNumLimit); //用户限制数
                                        request.getSession().setAttribute("billsNumLimit",billsNumLimit); //单据限制数
                                    }
                                }
                            }
                        }
                        request.getSession().setAttribute("mybatisPlusStatus","open"); //开启状态
                    } catch (Exception e) {
                        logger.error(">>>>>>>>>>>>>>>查询用户名为:" + username + " ，用户信息异常", e);
                    }
                    break;
            }
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("msgTip", msgTip);
            /**
             * 在IE模式下，无法获取到user数据，
             * 在此处明确添加上user信息
             * */
            if(user!=null){
                data.put("user",user);
            }
            res.code = 200;
            res.data = data;
            logger.info("===============用户登录 login 方法调用结束===============");
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "用户登录失败";
        }
        return res;
    }

    @GetMapping(value = "/getUserSession")
    public BaseResponseInfo getSessionUser(HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            Object userInfo = request.getSession().getAttribute("user");
            if(userInfo!=null) {
                User user = (User) userInfo;
                user.setPassword(null);
                data.put("user", user);
            }
            res.code = 200;
            res.data = data;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取session失败";
        }
        return res;
    }

    @GetMapping(value = "/logout")
    public BaseResponseInfo logout(HttpServletRequest request, HttpServletResponse response)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            request.getSession().removeAttribute("user");
            request.getSession().removeAttribute("mybatisPlusStatus");
            if(("open").equals(mybatisPlusStatus)) {
                request.getSession().removeAttribute("tenantId");
                request.getSession().removeAttribute("userNumLimit");
                request.getSession().removeAttribute("billsNumLimit");
            }
            response.sendRedirect("/login");
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "退出失败";
        }
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
