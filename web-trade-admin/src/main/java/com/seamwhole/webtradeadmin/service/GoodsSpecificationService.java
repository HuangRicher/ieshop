package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.GoodsSpecification;
import com.seamwhole.webtradeadmin.info.ShopGoodsSpecificationDO;
import com.seamwhole.webtradeadmin.service.impl.GoodsSpecificationServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = GoodsSpecificationServiceHystrix.class)
public interface GoodsSpecificationService {

    @PostMapping("/goodsSpecification/queryList")
    List<ShopGoodsSpecificationDO> queryList(@RequestBody Map<String, Object> params);

    @PostMapping("/goodsSpecification/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/goodsSpecification/update")
    void update(@RequestBody GoodsSpecification goodsSpecification);

    @PostMapping("/goodsSpecification/save")
    void save(@RequestBody GoodsSpecification goodsSpecification);

    @GetMapping("/goodsSpecification/queryObject/{id}")
    GoodsSpecification queryObject(@PathVariable("id") Integer id);

    @PostMapping("/goodsSpecification/queryByPage")
    PagesInfo<ShopGoodsSpecificationDO> queryByPage(@RequestBody Map<String, Object> params);
}
