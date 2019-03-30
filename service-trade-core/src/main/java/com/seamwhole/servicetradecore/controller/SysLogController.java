package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.SysLogModel;
import com.seamwhole.servicetradecore.model.SysLog;
import com.seamwhole.servicetradecore.service.SysLogService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统日志Controller
 */
@Controller
@RequestMapping("/old/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 系统日志列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public ResponseObject list(@RequestBody SysLogModel logModel) {
        //查询列表数据
        Map<String,Object> params=new HashMap<>();
        PageInfo<SysLog> pageInfo=sysLogService.queryPage(params,logModel.getPageNum(),logModel.getPageSize());
        return ResponseObject.ok().put("page", pageInfo);
    }

}
