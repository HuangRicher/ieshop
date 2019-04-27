package com.seamwhole.servicetradecore.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.annotation.IgnoreAuth;
import com.seamwhole.servicetradecore.model.ShopToken;
import com.seamwhole.servicetradecore.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    public static final String LOGIN_TOKEN_KEY = "X-ishop-Token";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,X-ishop-Token,X-URL-PATH");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(LOGIN_TOKEN_KEY);
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null ;
            try{
                JSONObject res = new JSONObject();
                res.put("errno","401");
                res.put("errmsg","请先登录");
                out = response.getWriter();
                out.append(res.toString());
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(500);
                return false;
            }
            //throw new CheckException("请先登录", 401);
        }

        //查询token信息
        ShopToken tokenEntity = tokenService.queryByToken(token);
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            //throw new CheckException("token失效，请重新登录", 401);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null ;
            try{
                JSONObject res = new JSONObject();
                res.put("errno","401");
                res.put("errmsg","请先登录");
                out = response.getWriter();
                out.append(res.toString());
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(500);
                return false;
            }
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId().intValue());

        return true;
    }
}
