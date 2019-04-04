package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.Order;
import com.seamwhole.webtradeadmin.info.OrderDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = OrderServiceHystrix.class)
public interface OrderService {

    @PostMapping("/order/queryByPage")
    PagesInfo<OrderDO> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/order/queryObject/{id}")
    OrderDO queryObject(@PathVariable("id") Integer id);

    @PostMapping("/order/save")
    void save(@RequestBody Order order);

    @PostMapping("/order/update")
    void update(@RequestBody Order order);

    @PostMapping("/order/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/order/queryList")
    List<OrderDO> queryList(@RequestBody Map<String, Object> params);

    @PostMapping("/order/queryTotal")
    int queryTotal(@RequestBody Map<String, Object> params);

    @GetMapping("/order/confirm/{id}")
    void confirm(@PathVariable("id") Integer id);

    @PostMapping("/order/sendGoods")
    void sendGoods(@RequestBody Order order);
}
