package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.TopicCategory;
import com.seamwhole.webtradeadmin.service.TopicCategoryService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topiccategory")
public class TopicCategoryController {
    @Autowired
    private TopicCategoryService topicCategoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("topiccategory:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<TopicCategory> page=topicCategoryService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("topiccategory:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        TopicCategory topicCategory = topicCategoryService.queryObject(id);
        return ResponseObject.ok().put("topicCategory", topicCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("topiccategory:save")
    public ResponseObject save(@RequestBody TopicCategory topicCategory) {
        topicCategoryService.save(topicCategory);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("topiccategory:update")
    public ResponseObject update(@RequestBody TopicCategory topicCategory) {
        topicCategoryService.update(topicCategory);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("topiccategory:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        topicCategoryService.deleteBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<TopicCategory> list = topicCategoryService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }
}
