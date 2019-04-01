package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.OrderGoods;
import com.seamwhole.webtradeadmin.service.impl.OrderGoodsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = OrderGoodsServiceHystrix.class)
public interface OrderGoodsService {

    @PostMapping("/queryByPage")
    PagesInfo<OrderGoods> queryByPage(@RequestBody Map<String, Object> params);

    @PostMapping("/save")
    void save(@RequestBody OrderGoods orderGoods);

    @GetMapping("/queryObject/{id}")
    OrderGoods queryObject(@PathVariable("id") Integer id);

    @PostMapping("/update")
    void update(@RequestBody OrderGoods orderGoods);

    @PostMapping("/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/queryList")
    List<OrderGoods> queryList(@RequestBody Map<String, Object> params);
}
