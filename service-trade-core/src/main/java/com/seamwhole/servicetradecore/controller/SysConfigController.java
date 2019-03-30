package com.seamwhole.servicetradecore.controller;


import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.SysConfigModel;
import com.seamwhole.servicetradecore.model.SysConfig;
import com.seamwhole.servicetradecore.service.SysConfigService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置信息Controller
 */
@RestController
@RequestMapping("/odl/sys/config")
public class SysConfigController{
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有配置列表
     */
    @RequestMapping("/list")
    public ResponseObject list(@RequestBody SysConfigModel configModel) {
        //查询列表数据
        Map<String,Object> params=new HashMap<>();
        if(StringUtils.isNotBlank(configModel.getKey()))
            params.put("key",configModel.getKey());
        PageInfo<SysConfig> pageInfo=sysConfigService.queryByPage(params,configModel.getPageNum(),configModel.getPageSize());
        return ResponseObject.ok().put("page", pageInfo);
    }


    /**
     * 根据主键获取配置信息
     */
    @RequestMapping("/info/{id}")
    public ResponseObject info(@PathVariable("id") Long id) {
        SysConfig config = sysConfigService.queryObject(id);
        return ResponseObject.ok().put("config", config);
    }

    /**
     * 新增配置
     */
    @RequestMapping("/save")
    public ResponseObject save(@RequestBody SysConfigModel configModel) {
        SysConfig config=new SysConfig();
        config.setKey(configModel.getKey());
        config.setValue(configModel.getValue());
        config.setRemark(configModel.getRemark());
        sysConfigService.save(config);
        return ResponseObject.ok();
    }

    /**
     * 修改配置
     */
    @RequestMapping("/update")
    public ResponseObject update(@RequestBody SysConfigModel configModel) {
        SysConfig config=new SysConfig();
        config.setId(configModel.getId());
        if (StringUtils.isNotBlank(configModel.getKey()))
            config.setKey(configModel.getKey());
        if(StringUtils.isNotBlank(configModel.getValue()))
            config.setValue(configModel.getValue());
        if(StringUtils.isNotBlank(configModel.getRemark()))
            config.setRemark(configModel.getRemark());
        sysConfigService.update(config);
        return ResponseObject.ok();
    }

    /**
     * 删除配置
     *
     * @param ids 主键集
     * @return R
     */
    @RequestMapping("/delete")
    public ResponseObject delete(@RequestBody Long[] ids) {
        sysConfigService.deleteBatch(ids);
        return ResponseObject.ok();
    }

}
