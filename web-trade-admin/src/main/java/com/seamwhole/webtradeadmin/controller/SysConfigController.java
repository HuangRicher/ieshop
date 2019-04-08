package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.webtradeadmin.service.SysConfigService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息Controller
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有配置列表
     * @param params 请求参数
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:config:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysConfigEntity> configList = sysConfigService.queryList(query);
        int total = sysConfigService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }


    /**
     * 根据主键获取配置信息
     * @param id 主键
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public ResponseObject info(@PathVariable("id") Long id) {
        SysConfigEntity config = sysConfigService.queryObject(id);

        return ResponseObject.ok().put("config", config);
    }

    /**
     * 新增配置
     * @param config 配置
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:config:save")
    public ResponseObject save(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);

        sysConfigService.save(config);

        return ResponseObject.ok();
    }

    /**
     * 修改配置
     * @param config 配置
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:config:update")
    public ResponseObject update(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);

        sysConfigService.update(config);

        return ResponseObject.ok();
    }

    /**
     * 删除配置
     * @param ids 主键集
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public ResponseObject delete(@RequestBody Long[] ids) {
        sysConfigService.deleteBatch(ids);

        return ResponseObject.ok();
    }

}
