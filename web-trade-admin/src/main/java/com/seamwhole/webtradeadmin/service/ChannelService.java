package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopChannel;
import com.seamwhole.webtradeadmin.service.impl.ChannelServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = ChannelServiceHystrix.class)
public interface ChannelService {
    @PostMapping("/channel/queryByPage")
    PagesInfo<ShopChannel> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/channel/queryObject/{id}")
    ShopChannel queryObject(@PathVariable("id") Integer id);

    @PostMapping("/channel/save")
    void save(@RequestBody ShopChannel channel);

    @PostMapping("/channel/update")
    void update(@RequestBody ShopChannel channel);

    @PostMapping("/channel/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/channel/queryList")
    List<ShopChannel> queryList(@RequestBody Map<String, Object> params);
}
