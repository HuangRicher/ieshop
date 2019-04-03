package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.ShopComment;
import com.seamwhole.webtradeadmin.info.ShopCommentDO;
import com.seamwhole.webtradeadmin.service.impl.CommentServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = CommentServiceHystrix.class)
public interface CommentService {

    @PostMapping("/comment/queryByPage")
    PagesInfo<ShopCommentDO> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/comment/queryObject/{id}")
    ShopComment queryObject(@PathVariable("id") Integer id);

    @PostMapping("/comment/save")
    void save(@RequestBody ShopComment comment);

    @PostMapping("/comment/update")
    void update(@RequestBody ShopComment comment);

    @PostMapping("/comment/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/comment/queryList")
    List<ShopCommentDO> queryList(@RequestBody Map<String, Object> params);

    @PostMapping("/comment/toggleStatus")
    void toggleStatus(@RequestBody ShopComment comment);

    @PostMapping("/comment/queryTotal")
    int queryTotal(@RequestBody Map<String, Object> params);
}
