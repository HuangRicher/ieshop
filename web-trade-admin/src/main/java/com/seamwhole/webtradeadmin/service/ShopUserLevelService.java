package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.UserLevel;
import com.seamwhole.webtradeadmin.service.impl.ShopUserLevelServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = ShopUserLevelServiceHystrix.class)
public interface ShopUserLevelService {

    @PostMapping("/shopUserLevel/queryShopUserLevelList")
    List<UserLevel> queryShopUserLevelList(@RequestBody Map<String, Object> params);


    @PostMapping("/shopUserLevel/deleteShopUserLevelBatch")
    void deleteShopUserLevelBatch(@RequestBody Integer[] ids);


    @PostMapping("/shopUserLevel/updateShopUserLevelById")
    void updateShopUserLevelById(@RequestBody UserLevel userLevel);


    @PostMapping("/shopUserLevel/saveShopUserLevel")
    void saveShopUserLevel(@RequestBody UserLevel userLevel);


    @PostMapping("/shopUserLevel/getShopUserLevelById/{id}")
    UserLevel getShopUserLevelById(@PathVariable("id") Integer id);


    @PostMapping("/shopUserLevel/queryShopUserLevel")
    PagesInfo<UserLevel> queryShopUserLevel(@RequestBody Map<String, Object> params);
}
