package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.GoodsIssue;
import com.seamwhole.webtradeadmin.service.impl.GoodsIssueServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = GoodsIssueServiceHystrix.class)
public interface GoodsIssueService {


    @PostMapping("/goodsIssue/queryByPage")
    PagesInfo<GoodsIssue> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/goodsIssue/queryObject/{id}")
    GoodsIssue queryObject(@PathVariable("id") Integer id);

    @PostMapping("/goodsIssue/save")
    void save(@RequestBody GoodsIssue goodsIssue);

    @PostMapping("/goodsIssue/update")
    void update(@RequestBody GoodsIssue goodsIssue);

    @PostMapping("/goodsIssue/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/goodsIssue/queryList")
    List<GoodsIssue> queryList(@RequestBody Map<String, Object> params);
}
