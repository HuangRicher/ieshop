package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.FootPrint;
import com.seamwhole.webtradeadmin.info.ShopFootPrintDO;
import com.seamwhole.webtradeadmin.service.impl.FootprintServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = FootprintServiceHystrix.class)
public interface FootprintService {

    @PostMapping("/footPrint/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/footPrint/update")
    void update(@RequestBody FootPrint footprint);

    @PostMapping("/footPrint/save")
    void save(@RequestBody FootPrint footprint);

    @GetMapping("/footPrint/queryObject/{id}")
    FootPrint queryObject(@PathVariable("id") Integer id);

    @PostMapping("/footPrint/queryByPage")
    PagesInfo<ShopFootPrintDO> queryByPage(@RequestBody Map<String, Object> params);
}
