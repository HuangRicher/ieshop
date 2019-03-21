package com.seamwhole.gatewayzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.seamwhole.gatewayzuul.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class MyZuulFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyZuulFilter.class);

    @Autowired
    private RedisService redisService;



    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //log.info("{} >>> {}", request.getMethod(), request.getRequestURL());
        /*try {
            StringBuffer method=request.getRequestURL();

            if(method.indexOf("/api-user/wxLogin/wxLogin")!=-1 ||
                    method.indexOf("/api-user/wxLogin/testLogin")!=-1 ||
                    method.indexOf("/api-user/userRole/addUserRole")!=-1||
                    method.indexOf("/api-user/wxLogin/wxLoginGame")!=-1){
                String requestBody=new MyRequestWrapper(request).getBody();
                JSONObject object= JSON.parseObject(requestBody);
                if(StringUtils.isBlank(object.getString("openId"))) {
                    log.warn("异常登陆！");
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(401);
                    HttpServletResponse response=ctx.getResponse();
                    response.setHeader("Content-type", "text/html;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter pw = response.getWriter();
                    pw.write("异常登陆！");
                }
            }else{
                String requestBody=new MyRequestWrapper(request).getBody();
                if (StringUtils.isNotBlank(requestBody)) {
                    JSONObject object= JSON.parseObject(requestBody);
                    if(StringUtils.isBlank(object.getString("userId")) ||
                            StringUtils.isNotBlank(object.getString("userId")) &&redisService.get(object.getString("userId")+"_login_")==null) {
                        log.warn("登陆超时！");
                        ctx.setSendZuulResponse(false);
                        ctx.setResponseStatusCode(401);
                        HttpServletResponse response=ctx.getResponse();
                        response.setHeader("Content-type", "text/html;charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter pw = response.getWriter();
                        pw.write("登陆超时,请登录！");
                    }else{
                        Object obj=redisService.get(object.getString("userId")+"_login_");
                        redisService.set(object.getString("userId")+"_login_",obj,60*60l);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }*/
        return null;
    }
}