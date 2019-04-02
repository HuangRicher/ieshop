package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.KeyWords;
import com.seamwhole.webtradeadmin.service.KeywordsService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 热闹关键词表Controller
 */
@RestController
@RequestMapping("keywords")
public class KeywordsController {

    @Autowired
    private KeywordsService keywordsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("keywords:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<KeyWords> page=keywordsService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("keywords:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        KeyWords keywords = keywordsService.queryObject(id);
        return ResponseObject.ok().put("keywords", keywords);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("keywords:save")
    public ResponseObject save(@RequestBody KeyWords keywords) {
        keywordsService.save(keywords);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("keywords:update")
    public ResponseObject update(@RequestBody KeyWords keywords) {
        keywordsService.update(keywords);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("keywords:delete")
    public ResponseObject delete(@RequestBody Integer[]ids) {
        keywordsService.deleteBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<KeyWords> list = keywordsService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }
}
