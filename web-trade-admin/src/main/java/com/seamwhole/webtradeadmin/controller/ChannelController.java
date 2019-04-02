package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopChannel;
import com.seamwhole.webtradeadmin.service.ChannelService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("channel:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<ShopChannel> page=channelService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("channel:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        ShopChannel channel = channelService.queryObject(id);
        return ResponseObject.ok().put("channel", channel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("channel:save")
    public ResponseObject save(@RequestBody ShopChannel channel) {
        channelService.save(channel);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("channel:update")
    public ResponseObject update(@RequestBody ShopChannel channel) {
        channelService.update(channel);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("channel:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        channelService.deleteBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<ShopChannel> list = channelService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }
}
