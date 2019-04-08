package com.seamwhole.webtradeadmin.controller;

import com.alibaba.fastjson.JSON;
import com.seamwhole.webtradeadmin.constant.Constant;
import com.seamwhole.webtradeadmin.service.SysConfigService;
import com.seamwhole.webtradeadmin.service.SysSmsLogService;
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
    private SysSmsLogService smsLogService;
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
        Query query = new Query(params);

        List<SysSmsLogEntity> smsLogList = smsLogService.queryList(query);
        int total = smsLogService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(smsLogList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 根据主键获取日志信息
     * @param id 主键
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:smslog:info")
    public ResponseObject info(@PathVariable("id") String id) {
        SysSmsLogEntity smsLog = smsLogService.queryObject(id);

        return ResponseObject.ok().put("smsLog", smsLog);
    }

    /**
     * 查看所有列表
     * @param params 请求参数
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<SysSmsLogEntity> list = smsLogService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 获取短信配置信息
     */
    @RequestMapping("/config")
    public ResponseObject config() {
        SmsConfig config = sysConfigService.getConfigObject(KEY, SmsConfig.class);
        return ResponseObject.ok().put("config", config);
    }

    /**
     * 保存短信配置信息
     * @param config 短信配置信息
     */
    @RequestMapping("/saveConfig")
    public ResponseObject saveConfig(@RequestBody SmsConfig config) {
        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));
        return ResponseObject.ok();
    }

    /**
     * 发送短信
     * @param smsLog 短信
     */
    @RequestMapping("/sendSms")
    public ResponseObject sendSms(@RequestBody SysSmsLogEntity smsLog) {
        SysSmsLogEntity sysSmsLogEntity = smsLogService.sendSms(smsLog);
        return ResponseObject.ok().put("result", sysSmsLogEntity);
    }
}
