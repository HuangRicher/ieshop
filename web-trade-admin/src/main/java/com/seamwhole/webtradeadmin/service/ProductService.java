package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.Product;
import com.seamwhole.webtradeadmin.info.ProductDO;
import com.seamwhole.webtradeadmin.service.impl.ProductServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = ProductServiceHystrix.class)
public interface ProductService {
    @PostMapping("/product/queryList")
    List<ProductDO> queryList(@RequestBody Map<String, Object> params);

    @PostMapping("/product/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/product/update")
    void update(@RequestBody Product product);

    @PostMapping("/product/save")
    void save(@RequestBody Product product);

    @PostMapping("/product/queryObject/{id}")
    Product queryObject(@PathVariable("id") Integer id);

    @PostMapping("/product/queryByPage")
    PagesInfo<ProductDO> queryByPage(@RequestBody Map<String, Object> params);
}
