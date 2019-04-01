package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("helptype")
public class HelpTypeController {
    @Autowired
    private HelpTypeService helpTypeService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("helptype:list")
    @ResponseBody
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<HelpTypeEntity> helpTypeList = helpTypeService.queryList(query);
        int total = helpTypeService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(helpTypeList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("helptype:info")
    @ResponseBody
    public ResponseObject info(@PathVariable("id") Integer id) {
        HelpTypeEntity helpType = helpTypeService.queryObject(id);

        return ResponseObject.ok().put("helpType", helpType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("helptype:save")
    @ResponseBody
    public ResponseObject save(@RequestBody HelpTypeEntity helpType) {
        helpTypeService.save(helpType);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("helptype:update")
    @ResponseBody
    public ResponseObject update(@RequestBody HelpTypeEntity helpType) {
        helpTypeService.update(helpType);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("helptype:delete")
    @ResponseBody
    public ResponseObject delete(@RequestBody Integer[]ids) {
        helpTypeService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<HelpTypeEntity> list = helpTypeService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
