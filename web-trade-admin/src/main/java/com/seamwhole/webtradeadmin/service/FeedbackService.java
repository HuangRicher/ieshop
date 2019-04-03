package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.FeedBack;
import com.seamwhole.webtradeadmin.service.impl.FeedbackServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = FeedbackServiceHystrix.class)
public interface FeedbackService {
    @PostMapping("/feedback/queryByPage")
    PagesInfo<FeedBack> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/feedback/queryObject/{msgId}")
    FeedBack queryObject(@PathVariable("msgId") Integer msgId);

    @PostMapping("/feedback/save")
    void save(@RequestBody FeedBack feedback);

    @PostMapping("/feedback/update")
    void update(@RequestBody FeedBack feedback);

    @PostMapping("/feedback/deleteBatch")
    void deleteBatch(@RequestBody Integer[] msgIds);

    @PostMapping("/feedback/queryList")
    List<FeedBack> queryList(@RequestBody Map<String, Object> params);
}
