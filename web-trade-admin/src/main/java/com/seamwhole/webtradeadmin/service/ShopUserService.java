package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopUser;
import com.seamwhole.webtradeadmin.info.ShopUserDO;
import com.seamwhole.webtradeadmin.service.impl.ShopUserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = ShopUserServiceHystrix.class)
public interface ShopUserService {

    @PostMapping("/shopUser/queryShopUseByPage")
    PagesInfo<ShopUserDO> queryShopUseByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/shopUser/getShopUserById/{userId}")
    ShopUser getShopUserById(@PathVariable("userId") Integer userId);

    @PostMapping("/shopUser/saveShopUser")
    void saveShopUser(@RequestBody ShopUser user);

    @PostMapping("/shopUser/updateShopUserById")
    void updateShopUserById(@RequestBody ShopUser user);

    @PostMapping("/shopUser/deleteShopUserBatch")
    void deleteShopUserBatch(@RequestBody Integer[] ids);

    @PostMapping("/shopUser/queryShopUserList")
    List<ShopUserDO> queryShopUserList(@RequestBody Map<String, Object> params);

    @PostMapping("/shopUser/queryShopUserTotal")
    int queryShopUserTotal(@RequestBody Map<String, Object> params);
}
