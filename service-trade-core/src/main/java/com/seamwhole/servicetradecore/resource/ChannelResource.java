package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ShopChannel;
import com.seamwhole.servicetradecore.service.ChannelService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/channel")
public class ChannelResource {

    @Autowired
    private ChannelService channelService;


    @PostMapping("/queryByPage")
    public PagesInfo<ShopChannel> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopChannel> pageInfo=channelService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopChannel>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public ShopChannel queryObject(@PathVariable("id") Integer id){
        return channelService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ShopChannel channel){
        channelService.save(channel);
    }

    @PostMapping("/update")
    public void update(@RequestBody ShopChannel channel){
        channelService.update(channel);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        channelService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<ShopChannel> queryList(@RequestBody Map<String, Object> params){
        return channelService.queryList(params);
    }
}
