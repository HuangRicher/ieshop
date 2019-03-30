package com.seamwhole.servicetradecore.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.SysSmsLogModel;
import com.seamwhole.servicetradecore.domain.SmsConfigOutInfo;
import com.seamwhole.servicetradecore.mapper.model.SysSmsLogDO;
import com.seamwhole.servicetradecore.model.SysSmsLog;
import com.seamwhole.servicetradecore.model.SysSmsLogWithBLOBs;
import com.seamwhole.servicetradecore.service.SysConfigService;
import com.seamwhole.servicetradecore.service.SysSmsLogService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送短信日志Controller
 */
@RestController
@RequestMapping("/old/sys/smslog")
public class SysSmsLogController {
    @Autowired
    private SysSmsLogService sysSmsLogService;
    @Autowired
    private SysConfigService sysConfigService;
    /**
     * 短信配置KEY
     */
    private final static String KEY = Constant.SMS_CONFIG_KEY;

    /**
     * 所有日志列表
     */
    @RequestMapping("/list")
    public ResponseObject list(@RequestBody SysSmsLogModel logModel) {
        //查询列表数据
        Map<String,Object> params=new HashMap<>();
        PageInfo<SysSmsLogDO> pageInfo=sysSmsLogService.queryByPage(params,logModel.getPageNum(),logModel.getPageSize());
        return ResponseObject.ok().put("page", pageInfo);
    }

    /**
     * 根据主键获取日志信息
     */
    @RequestMapping("/info/{id}")
    public ResponseObject info(@PathVariable("id") String id) {
        SysSmsLog smsLog = sysSmsLogService.queryObject(id);
        return ResponseObject.ok().put("smsLog", smsLog);
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<SysSmsLogDO> list = sysSmsLogService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 获取短信配置信息
     */
    @RequestMapping("/config")
    public ResponseObject config() {
        SmsConfigOutInfo config = sysConfigService.getConfigObject(KEY, SmsConfigOutInfo.class);
        return ResponseObject.ok().put("config", config);
    }

    /**
     * 保存短信配置信息
     */
    @RequestMapping("/saveConfig")
    public ResponseObject saveConfig(@RequestBody SmsConfigOutInfo config) {
        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));
        return ResponseObject.ok();
    }

    /**
     * 发送短信
     */
    @RequestMapping("/sendSms")
    public ResponseObject sendSms(@RequestBody SysSmsLogWithBLOBs smsLog,Long userId) {
        SysSmsLogWithBLOBs sysSmsLogEntity = sysSmsLogService.sendSms(smsLog,userId);
        return ResponseObject.ok().put("result", sysSmsLogEntity);
    }
}
