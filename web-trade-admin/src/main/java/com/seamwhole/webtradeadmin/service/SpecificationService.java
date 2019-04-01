package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.Specification;
import com.seamwhole.webtradeadmin.service.impl.SpecificationServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SpecificationServiceHystrix.class)
public interface SpecificationService {

    @PostMapping("/specification/queryByPage")
    PagesInfo<Specification> queryByPage(@RequestBody Map<String, Object> params);

    @PostMapping("/specification/queryObject/{id}")
    Specification queryObject(@PathVariable("id") Integer id);

    @PostMapping("/specification/save")
    void save(@RequestBody Specification specification);

    @PostMapping("/specification/update")
    void update(@RequestBody Specification specification);

    @PostMapping("/specification/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/specification/queryList")
    List<Specification> queryList(@RequestBody Map<String, Object> params);
}
