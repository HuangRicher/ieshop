package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.HelpType;
import com.seamwhole.webtradeadmin.service.impl.HelpTypeServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = HelpTypeServiceHystrix.class)
public interface HelpTypeService {

    @PostMapping("/helpType/queryByPage")
    PagesInfo<HelpType> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/helpType/queryObject/{id}")
    HelpType queryObject(@PathVariable("id") Integer id);

    @PostMapping("/helpType/save")
    void save(@RequestBody HelpType helpType);

    @PostMapping("/helpType/update")
    void update(@RequestBody HelpType helpType);

    @PostMapping("/helpType/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/helpType/queryList")
    List<HelpType> queryList(@RequestBody Map<String, Object> params);
}
