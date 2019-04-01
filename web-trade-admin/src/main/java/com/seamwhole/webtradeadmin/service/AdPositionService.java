package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.AdPosition;
import com.seamwhole.webtradeadmin.service.impl.AdPositionServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = AdPositionServiceHystrix.class)
public interface AdPositionService {

    @PostMapping("/adPosition/queryByPage")
    PagesInfo<AdPosition> queryByPage(@RequestBody Map<String, Object> params);

    @PostMapping("/adPosition/queryObject/{id}")
    AdPosition queryObject(@PathVariable("id") Integer id);

    @PostMapping("/adPosition/save")
    void save(@RequestBody AdPosition adPosition);

    @PostMapping("/adPosition/update")
    void update(@RequestBody AdPosition adPosition);

    @PostMapping("/adPosition/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/adPosition/queryList")
    List<AdPosition> queryList(@RequestBody Map<String, Object> params);
}
