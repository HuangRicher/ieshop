package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.KeyWords;
import com.seamwhole.webtradeadmin.service.impl.KeywordsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = KeywordsServiceHystrix.class)
public interface KeywordsService {
    @PostMapping("/keyWords/queryByPage")
    PagesInfo<KeyWords> queryByPage(Map<String, Object> params);

    @GetMapping("/keyWords/queryObject/{id}")
    KeyWords queryObject(@PathVariable("id") Integer id);

    @PostMapping("/keyWords/save")
    void save(KeyWords keywords);

    @PostMapping("/keyWords/update")
    void update(KeyWords keywords);

    @PostMapping("/keyWords/deleteBatch")
    void deleteBatch(Integer[] ids);

    @PostMapping("/keyWords/queryList")
    List<KeyWords> queryList(Map<String, Object> params);
}
