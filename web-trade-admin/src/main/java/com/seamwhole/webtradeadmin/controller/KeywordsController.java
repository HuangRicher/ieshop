package com.seamwhole.webtradeadmin.controller;

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
        Query query = new Query(params);

        List<KeywordsEntity> keywordsList = keywordsService.queryList(query);
        int total = keywordsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(keywordsList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("keywords:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        KeywordsEntity keywords = keywordsService.queryObject(id);

        return ResponseObject.ok().put("keywords", keywords);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("keywords:save")
    public ResponseObject save(@RequestBody KeywordsEntity keywords) {
        keywordsService.save(keywords);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("keywords:update")
    public ResponseObject update(@RequestBody KeywordsEntity keywords) {
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

        List<KeywordsEntity> list = keywordsService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
