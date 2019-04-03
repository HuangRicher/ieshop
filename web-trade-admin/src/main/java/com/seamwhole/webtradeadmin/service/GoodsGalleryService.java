package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.GoodsGallery;
import com.seamwhole.webtradeadmin.info.GoodsGalleryDO;
import com.seamwhole.webtradeadmin.service.impl.GoodsGalleryServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = GoodsGalleryServiceHystrix.class)
public interface GoodsGalleryService {

    @PostMapping("/goodsGallery/queryByPage")
    PagesInfo<GoodsGalleryDO> queryByPage(@RequestBody Map<String, Object> params);

    @PostMapping("/goodsGallery/queryObject/{id}")
    GoodsGallery queryObject(@PathVariable("id") Integer id);

    @PostMapping("/goodsGallery/save")
    void save(@RequestBody GoodsGallery goodsGallery);

    @PostMapping("/goodsGallery/update")
    void update(@RequestBody GoodsGallery goodsGallery);

    @PostMapping("/goodsGallery/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/goodsGallery/queryList")
    List<GoodsGalleryDO> queryList(@RequestBody Map<String, Object> params);
}
