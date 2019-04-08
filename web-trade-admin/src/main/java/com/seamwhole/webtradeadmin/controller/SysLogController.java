package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.webtradeadmin.service.SysLogService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 系统日志Controller
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 系统日志列表
     * @param params 请求参数
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:log:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageUtilsPlus pageUtil = sysLogService.queryPage(params);
        return ResponseObject.ok().put("page", pageUtil);
    }

}
