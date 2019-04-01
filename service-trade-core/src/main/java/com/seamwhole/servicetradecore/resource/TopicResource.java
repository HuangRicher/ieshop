package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ShopTopic;
import com.seamwhole.servicetradecore.service.TopicService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topic")
public class TopicResource {

    @Autowired
    private TopicService topicService;

    @PostMapping("/queryByPage")
    public PagesInfo<ShopTopic> queryByPage(@RequestBody Map<String, Object> params){
       PageInfo<ShopTopic> pageInfo= topicService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
       return new PagesInfo<ShopTopic>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public ShopTopic queryObject(@PathVariable("id") Integer id){
        return topicService.queryObject(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ShopTopic topic){
        topicService.save(topic);
    }

    @PostMapping("/update")
    public void update(@RequestBody ShopTopic topic){
        topicService.updateById(topic);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        topicService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<ShopTopic> queryList(@RequestBody Map<String, Object> params){
        return topicService.queryList(params);
    }
}
