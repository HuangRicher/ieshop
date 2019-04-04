package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.Goods;
import com.seamwhole.webtradeadmin.info.GoodsModel;
import com.seamwhole.webtradeadmin.info.ShopGoodsDO;
import com.seamwhole.webtradeadmin.service.impl.GoodsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = GoodsServiceHystrix.class)
public interface GoodsService {
    @PostMapping("/goods/queryByPage")
    PagesInfo<ShopGoodsDO> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/goods/queryObject/{id}")
    Goods queryObject(@PathVariable("id") Integer id);

    @PostMapping("/goods/save/{userId}/{deptId}")
    void save(@RequestBody GoodsModel goods,@PathVariable("userId") Long userId, @PathVariable("deptId") Long deptId);

    @PostMapping("/goods/update")
    void update(@RequestBody Goods goods);

    @PostMapping("/goods/deleteBatch/{userId}")
    void deleteBatch(@RequestBody Integer[] ids,@PathVariable("userId") Long userId);

    @PostMapping("/goods/queryList")
    List<ShopGoodsDO> queryList(@RequestBody Map<String, Object> params);

    @PostMapping("/goods/back/{userId}")
    void back(@RequestBody Integer[] ids,@PathVariable("userId") Long userId);

    @PostMapping("/goods/queryTotal")
    int queryTotal(@RequestBody Map<String, Object> params);

    @PostMapping("/goods/enSale/{userId}/{id}")
    void enSale(@PathVariable("id") Integer id,@PathVariable("userId") Long userId);

    @PostMapping("/goods/unSale/{userId}/{id}")
    void unSale(@PathVariable("id") Integer id,@PathVariable("userId") Long userId);
}
