package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopTopic;
import com.seamwhole.webtradeadmin.service.impl.TopicServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = TopicServiceHystrix.class)
public interface TopicService {

    @PostMapping("/topic/queryByPage")
    PagesInfo<ShopTopic> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/topic/queryObject/{id}")
    ShopTopic queryObject(@PathVariable("id") Integer id);

    @PostMapping("/topic/save")
    void save(@RequestBody ShopTopic topic);

    @PostMapping("/topic/update")
    void update(@RequestBody ShopTopic topic);

    @PostMapping("/topic/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/topic/queryList")
    List<ShopTopic> queryList(@RequestBody Map<String, Object> params);
}
