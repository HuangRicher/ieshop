package com.seamwhole.webtradeadmin.controller;

import com.alibaba.fastjson.JSON;
import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.constant.Constant;
import com.seamwhole.webtradeadmin.info.SmsConfigOutInfo;
import com.seamwhole.webtradeadmin.info.SysSmsLog;
import com.seamwhole.webtradeadmin.info.SysSmsLogDO;
import com.seamwhole.webtradeadmin.info.SysSmsLogWithBLOBs;
import com.seamwhole.webtradeadmin.service.SysConfigService;
import com.seamwhole.webtradeadmin.service.SysSmsLogService;
import com.seamwhole.webtradeadmin.shiro.ShiroUtils;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 发送短信日志Controller
 */
@RestController
@RequestMapping("/sys/smslog")
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
     * @param params 请求参数
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:smslog:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<SysSmsLogDO> page=sysSmsLogService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 根据主键获取日志信息
     * @param id 主键
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:smslog:info")
    public ResponseObject info(@PathVariable("id") String id) {
        SysSmsLog smsLog = sysSmsLogService.queryObject(id);

        return ResponseObject.ok().put("smsLog", smsLog);
    }

    /**
     * 查看所有列表
     * @param params 请求参数
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
     * @param config 短信配置信息
     */
    @RequestMapping("/saveConfig")
    public ResponseObject saveConfig(@RequestBody SmsConfigOutInfo config) {
        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));
        return ResponseObject.ok();
    }

    /**
     * 发送短信
     * @param smsLog 短信
     */
    @RequestMapping("/sendSms")
    public ResponseObject sendSms(@RequestBody SysSmsLogWithBLOBs smsLog) {
        SysSmsLogWithBLOBs sysSmsLogEntity = sysSmsLogService.sendSms(smsLog, ShiroUtils.getUserId());
        return ResponseObject.ok().put("result", sysSmsLogEntity);
    }
}
