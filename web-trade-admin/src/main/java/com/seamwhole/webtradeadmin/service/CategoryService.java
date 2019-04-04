package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.Category;
import com.seamwhole.webtradeadmin.service.impl.CategoryServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = CategoryServiceHystrix.class)
public interface CategoryService {

    @PostMapping("/category/queryByPage")
    PagesInfo<Category> queryByPage(@RequestBody Map<String, Object> params);

    @PostMapping("/category/queryObject/{id}")
    Category queryObject(@PathVariable("id") Integer id);

    @PostMapping("/category/save")
    void save(@RequestBody Category category);

    @PostMapping("/category/update")
    void update(@RequestBody Category category);

    @PostMapping("/category/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/category/queryList")
    List<Category> queryList(@RequestBody Map<String, Object> params);
}
