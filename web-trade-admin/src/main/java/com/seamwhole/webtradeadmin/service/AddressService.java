package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.AddressDO;
import com.seamwhole.webtradeadmin.info.ShopAddress;
import com.seamwhole.webtradeadmin.service.impl.AddressServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = AddressServiceHystrix.class)
public interface AddressService {

    @PostMapping("/shopAddress/queryShopUserAddressByPage")
    PagesInfo<AddressDO> queryShopUserAddressByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/shopAddress/queryObject/{id}")
    AddressDO queryObject(@PathVariable("id") Integer id);

    @PostMapping("/shopAddress/save")
    void save(@RequestBody ShopAddress address);

    @PostMapping("/shopAddress/update")
    void update(@RequestBody ShopAddress address);

    @PostMapping("/shopAddress/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);
}
