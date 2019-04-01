package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopAd;
import com.seamwhole.webtradeadmin.info.ShopAdDO;
import com.seamwhole.webtradeadmin.service.impl.ShopAdServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = ShopAdServiceHystrix.class)
public interface ShopAdService {
    @PostMapping("/shopAd/queryByPage")
    PagesInfo<ShopAdDO> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/shopAd/queryObject/{id}")
    ShopAd queryObject(@PathVariable("id") Integer id);

    @PostMapping("/shopAd/save")
    void save(@RequestBody ShopAd ad);

    @PostMapping("/shopAd/update")
    void update(@RequestBody ShopAd ad);

    @PostMapping("/shopAd/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/shopAd/queryList")
    List<ShopAdDO> queryList(@RequestBody Map<String, Object> params);
}
