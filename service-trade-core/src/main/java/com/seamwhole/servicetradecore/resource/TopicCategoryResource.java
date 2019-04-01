package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.TopicCategory;
import com.seamwhole.servicetradecore.service.TopicCategoryService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topicCategory")
public class TopicCategoryResource {

    @Autowired
    private TopicCategoryService topicCategoryService;


    @PostMapping("/queryByPage")
    public PagesInfo<TopicCategory> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<TopicCategory> page=topicCategoryService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return  new PagesInfo<TopicCategory>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }

    @GetMapping("/queryObject/{id}")
    public TopicCategory queryObject(@PathVariable("id") Integer id){
        return topicCategoryService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody TopicCategory topicCategory){
        topicCategoryService.save(topicCategory);
    }

    @PostMapping("/update")
    public void update(@RequestBody TopicCategory topicCategory){
        topicCategoryService.updateById(topicCategory);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        topicCategoryService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<TopicCategory> queryList(@RequestBody Map<String, Object> params){
        return topicCategoryService.queryList(params);
    }
}
