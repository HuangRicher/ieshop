package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.TopicCategory;
import com.seamwhole.webtradeadmin.service.impl.TopicCategoryServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = TopicCategoryServiceHystrix.class)
public interface TopicCategoryService {

    @PostMapping("/topicCategory/queryByPage")
    PagesInfo<TopicCategory> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/topicCategory/queryObject/{id}")
    TopicCategory queryObject(@PathVariable("id") Integer id);

    @PostMapping("/topicCategory/save")
    void save(@RequestBody TopicCategory topicCategory);

    @PostMapping("/topicCategory/update")
    void update(@RequestBody TopicCategory topicCategory);

    @PostMapping("/topicCategory/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/topicCategory/queryList")
    List<TopicCategory> queryList(@RequestBody Map<String, Object> params);
}
