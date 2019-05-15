package com.seamwhole.serviceerpcore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.mapper.vo.UserEx;
import com.seamwhole.serviceerpcore.mapper.vo.TreeNodeEx;
import com.seamwhole.serviceerpcore.exception.BusinessParamCheckingException;
import com.seamwhole.serviceerpcore.service.UserService;
import com.seamwhole.serviceerpcore.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.seamwhole.serviceerpcore.utils.ResponseJsonUtil.returnJson;


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
    private UserService userService;

    private static String message = "成功";
    private static final String HTTP = "http://";
    private static final String CODE_OK = "200";

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
                userStatus = userService.validateUser(username, password);
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
                        user = userService.getUserByUserName(username);
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
                        request.getSession().setAttribute("mybatisPlusStatus",mybatisPlusStatus); //开启状态
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


    @PostMapping(value = "/resetPwd")
    public String resetPwd(@RequestParam("id") Long id) throws Exception {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        String password = "123456";
        String md5Pwd = Tools.md5Encryp(password);
        int update = userService.resetPwd(md5Pwd, id);
        if(update > 0) {
            return returnJson(objectMap, message, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, message, ErpInfo.ERROR.code);
        }
    }

    @PostMapping(value = "/updatePwd/{userId}/{password}/{oldpwd}")
    public String updatePwd(@PathVariable("userId") Long userId,
                            @PathVariable("password") String password,
                            @PathVariable("oldpwd") String oldpwd)throws Exception {
        Integer flag = 0;
        Map<String, Object> objectMap = new HashMap<String, Object>();
        try {
            User user = userService.getUser(userId);
            String oldPassword = Tools.md5Encryp(oldpwd);
            String md5Pwd = Tools.md5Encryp(password);
            //必须和原始密码一致才可以更新密码
            if(user.getLoginame().equals("jsh")){
                flag = 3; //管理员jsh不能修改密码
            } else if (oldPassword.equalsIgnoreCase(user.getPassword())) {
                user.setPassword(md5Pwd);
                flag = userService.updateUserByObj(user); //1-成功
            } else {
                flag = 2; //原始密码输入错误
            }
            objectMap.put("status", flag);
            if(flag > 0) {
                return returnJson(objectMap, message, ErpInfo.OK.code);
            } else {
                return returnJson(objectMap, message, ErpInfo.ERROR.code);
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>>修改用户ID为 ： " + userId + "密码信息失败", e);
            flag = 3;
            objectMap.put("status", flag);
            return returnJson(objectMap, message, ErpInfo.ERROR.code);
        }
    }

    /**
     * 获取全部用户数据列表
     * @return
     */
    @GetMapping(value = "/getAllList")
    public BaseResponseInfo getAllList()throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            List<User> dataList = userService.getUser();
            if(dataList!=null) {
                data.put("userList", dataList);
            }
            res.code = 200;
            res.data = data;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取失败";
        }
        return res;
    }


    /**
     *  查询分页用户列表
     * @Param: pageSize
     * @Param: currentPage
     * @Param: search
     * @return java.lang.String
     */
    @GetMapping(value = "/getUserList/{pageSize}/{currentPage}/{search}")
    public String getUserList(@PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                              @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                              @PathVariable(value = Constants.SEARCH, required = false) String search)throws Exception {

        Map<String, Object> parameterMap = new HashMap<String, Object>();
        //查询参数
        JSONObject obj= JSON.parseObject(search);
        Set<String> key= obj.keySet();
        for(String keyEach: key){
            parameterMap.put(keyEach,obj.getString(keyEach));
        }
        PageQueryInfo queryInfo = new PageQueryInfo();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        if (pageSize == null || pageSize <= 0) {
            pageSize = BusinessConstants.DEFAULT_PAGINATION_PAGE_SIZE;
        }
        if (currentPage == null || currentPage <= 0) {
            currentPage = BusinessConstants.DEFAULT_PAGINATION_PAGE_NUMBER;
        }
        PageHelper.startPage(currentPage,pageSize,true);
        List<UserEx> list = userService.getUserList(parameterMap);
        //获取分页查询后的数据
        PageInfo<UserEx> pageInfo = new PageInfo<>(list);
        objectMap.put("page", queryInfo);
        if (list == null) {
            queryInfo.setRows(new ArrayList<Object>());
            queryInfo.setTotal(BusinessConstants.DEFAULT_LIST_NULL_NUMBER);
            return returnJson(objectMap, "查找不到数据", ErpInfo.OK.code);
        }
        queryInfo.setRows(list);
        queryInfo.setTotal(pageInfo.getTotal());
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }

    /**
     *  新增用户及机构和用户关系
     * @Param: beanJson
     * @return java.lang.Object
     */
    @PostMapping("/addUser")
    public Object addUser(@RequestParam("info") String beanJson)throws Exception{
        JSONObject result = ExceptionConstants.standardSuccess();
        if(("open").equals(mybatisPlusStatus)) {
            Long userNumLimit = 10000000L; //Long.parseLong(request.getSession().getAttribute("userNumLimit").toString());
            Long count = userService.countUser(null,null);
            if(count>= userNumLimit) {
                throw new BusinessParamCheckingException(ExceptionConstants.USER_OVER_LIMIT_FAILED_CODE,
                        ExceptionConstants.USER_OVER_LIMIT_FAILED_MSG);
            } else {
                UserEx ue= JSON.parseObject(beanJson, UserEx.class);
                userService.addUserAndOrgUserRel(ue);
            }
        } else {
            UserEx ue= JSON.parseObject(beanJson, UserEx.class);
            userService.addUserAndOrgUserRel(ue);
        }
        return result;
    }


    /**
     * 注册用户
     * @param loginame
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/registerUser")
    public Object registerUser(@RequestParam(value = "loginame", required = false) String loginame,
                               @RequestParam(value = "password", required = false) String password)throws Exception{
        JSONObject result = ExceptionConstants.standardSuccess();
        UserEx ue= new UserEx();
        ue.setUsername(loginame);
        ue.setLoginame(loginame);
        ue.setPassword(password);
        userService.checkUserNameAndLoginName(ue); //检查用户名和登录名
        ue = userService.registerUser(ue,manageRoleId);
        /**
         * create by: qiankunpingtai
         * create time: 2019/4/9 17:17
         * website：https://qiankunpingtai.cn
         * description:
         * 这里涉及到多个项目，需要用分布式事务去处理
         * 为了不使问题复杂化，暂时另外开启一个线程去处理其它项目的数据操作
         */
        final UserEx ueFinal=ue;
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
                    try{
                        //调第三方接口创建租户管理信息
                        String url = HTTP + manageIp + ":" + managePort + "/tenant/add";
                        JSONObject tenantObj = new JSONObject();
                        tenantObj.put("tenantId", ueFinal.getId());
                        tenantObj.put("loginName",ueFinal.getLoginame());
                        String param = URLEncoder.encode(tenantObj.toString());
                        HttpClient.httpPost(url + "?info=" + param, param);
                        logger.info("===============创建租户信息完成===============");
                    }catch(Exception e){
                        //记录一下第三方接口创建租户管理信息创建失败
                        logger.debug("调用第三方接口创建租户管理信息失败：tenantId：[{}],loginName:[{}]",ueFinal.getId(),ueFinal.getLoginame());
                    }
                });

        return result;
    }


    /**
     *  修改用户及机构和用户关系
     * @Param: beanJson
     * @return java.lang.Object
     */
    @PostMapping("/updateUser")
    public Object updateUser(@RequestParam("info") String beanJson,
                             @RequestParam("id") Long id)throws Exception{
        JSONObject result = ExceptionConstants.standardSuccess();
        UserEx ue= JSON.parseObject(beanJson, UserEx.class);
        ue.setId(id);
        userService.updateUserAndOrgUserRel(ue);
        return result;
    }


    @PostMapping("/deleteUser")
    public Object deleteUser(@RequestParam("ids") String ids)throws Exception{
        JSONObject result = ExceptionConstants.standardSuccess();
        userService.batDeleteUser(ids);
        return result;
    }


    @PostMapping("/batchDeleteUser")
    public Object batchDeleteUser(@RequestParam("ids") String ids)throws Exception{
        JSONObject result = ExceptionConstants.standardSuccess();
        userService.batDeleteUser(ids);
        return result;
    }

    @RequestMapping("/getOrganizationUserTree")
    public JSONArray getOrganizationUserTree()throws Exception{
        JSONArray arr=new JSONArray();
        List<TreeNodeEx> organizationUserTree= userService.getOrganizationUserTree();
        if(organizationUserTree!=null&&organizationUserTree.size()>0){
            for(TreeNodeEx node:organizationUserTree){
                String str=JSON.toJSONString(node);
                JSONObject obj=JSON.parseObject(str);
                arr.add(obj) ;
            }
        }
        return arr;
    }

    @GetMapping("/getTenantStatus")
    public BaseResponseInfo getTenantStatus()throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("status", mybatisPlusStatus);
            res.code = 200;
            res.data = data;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取失败";
        }
        return res;
    }

    @GetMapping("/getUserByUserName")
    public User getUserByUserName(@RequestParam("username") String username) throws Exception{
        return userService.getUserByUserName(username);
    }

    @GetMapping("/validateUser")
    public Integer validateUser(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception{
        return userService.validateUser(username,password);
    }
}
