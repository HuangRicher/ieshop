package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.RelatedGoods;
import com.seamwhole.webtradeadmin.service.impl.RelatedGoodsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = RelatedGoodsServiceHystrix.class)
public interface RelatedGoodsService {

    @PostMapping("/relatedGoods/queryByPage")
    PagesInfo<RelatedGoods> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/relatedGoods/queryObject/{id}")
    RelatedGoods queryObject(@PathVariable("id") Integer id);

    @PostMapping("/relatedGoods/save")
    void save(@RequestBody RelatedGoods relatedGoods);

    @PostMapping("/relatedGoods/update")
    void update(@RequestBody RelatedGoods relatedGoods);

    @PostMapping("/relatedGoods/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);
}
