package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopCart;
import com.seamwhole.webtradeadmin.info.ShopCartDO;
import com.seamwhole.webtradeadmin.service.impl.CartServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = CartServiceHystrix.class)
public interface CartService {

    @PostMapping("/shopCart/queryByPage")
    PagesInfo<ShopCartDO> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/shopCart/queryObject/{id}")
    ShopCart queryObject(@PathVariable("id") Integer id);

    @PostMapping("/shopCart/save")
    void save(@RequestBody ShopCart cart);

    @PostMapping("/shopCart/update")
    void update(@RequestBody ShopCart cart);

    @PostMapping("/shopCart/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);
}
