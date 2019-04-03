package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopCollect;
import com.seamwhole.webtradeadmin.info.ShopCollectDO;
import com.seamwhole.webtradeadmin.service.impl.CollectServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = CollectServiceHystrix.class)
public interface CollectService {

    @PostMapping("/shopCollect/queryByPage")
    PagesInfo<ShopCollectDO> queryByPage(@RequestBody Map<String, Object> params);

    @PostMapping("/shopCollect/queryObject/{id}")
    ShopCollect queryObject(@PathVariable("id") Integer id);

    @PostMapping("/shopCollect/save")
    void save(@RequestBody ShopCollect collect);

    @PostMapping("/shopCollect/update")
    void update(@RequestBody ShopCollect collect);

    @PostMapping("/shopCollect/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);
}
