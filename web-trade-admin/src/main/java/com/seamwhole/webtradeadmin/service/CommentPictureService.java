package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.CommentPicture;
import com.seamwhole.webtradeadmin.service.impl.CommentPictureServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = CommentPictureServiceHystrix.class)
public interface CommentPictureService {

    @PostMapping("/commentPicture/queryByPage")
    PagesInfo<CommentPicture> queryByPage(@RequestBody Map<String, Object> params);

    @PostMapping("/commentPicture/queryObject/{id}")
    CommentPicture queryObject(@PathVariable("id") Integer id);

    @PostMapping("/commentPicture/save")
    void save(@RequestBody CommentPicture commentPicture);

    @PostMapping("/commentPicture/update")
    void update(@RequestBody CommentPicture commentPicture);

    @PostMapping("/commentPicture/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/commentPicture/queryList")
    List<CommentPicture> queryList(@RequestBody Map<String, Object> params);
}
