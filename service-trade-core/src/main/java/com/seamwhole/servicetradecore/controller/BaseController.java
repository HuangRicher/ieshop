package com.seamwhole.servicetradecore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: BaseAction
 * @Description: 基础控制类
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * @param requestCode
     * @param msg
     * @param data
     * @return Map<String,Object>
     * @throws
     * @Description:构建统一格式返回对象
     */
    public Map<String, Object> toResponsObject(int requestCode, String msg, Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", requestCode);
        obj.put("errmsg", msg);
        if (data != null)
            obj.put("data", data);
        return obj;
    }

    public Map<String, Object> toResponsSuccess(Object data) {
        Map<String, Object> rp = toResponsObject(0, "执行成功", data);
        logger.info("response:" + rp);
        return rp;
    }

    public Map<String, Object> toResponsMsgSuccess(String msg) {
        return toResponsObject(0, msg, "");
    }

    public Map<String, Object> toResponsSuccessForSelect(Object data) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("list", data);
        return toResponsObject(0, "执行成功", result);
    }

    public Map<String, Object> toResponsFail(String msg) {
        return toResponsObject(1, msg, null);
    }

    /**
     * initBinder 初始化绑定 <br>
     * 这里处理了3种类型<br>
     * 1、字符串自动 trim 去掉前后空格 <br>
     * 2、java.util.Date 转换为 "yyyy-MM-dd HH:mm:ss" 格式<br>
     * 3、java.sql.Date 转换为 "yyyy-MM-dd" 格式<br>
     * 4、java.util.Timestamps 时间转换
     *
     * @param binder  WebDataBinder 要注册的binder
     * @param request 前端请求
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        // 字符串自动Trim
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

}
