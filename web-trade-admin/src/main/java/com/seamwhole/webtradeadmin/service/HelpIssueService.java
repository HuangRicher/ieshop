package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.HelpIssue;
import com.seamwhole.webtradeadmin.info.HelpIssueDO;
import com.seamwhole.webtradeadmin.service.impl.HelpIssueServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = HelpIssueServiceHystrix.class)
public interface HelpIssueService {

    @PostMapping("/helpIssue/queryByPage")
    PagesInfo<HelpIssueDO> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/helpIssue/queryObject/{id}")
    HelpIssue queryObject(@PathVariable("id") Integer id);

    @PostMapping("/helpIssue/save")
    void save(@RequestBody HelpIssue helpIssue);

    @PostMapping("/helpIssue/update")
    void update(@RequestBody HelpIssue helpIssue);

    @PostMapping("/helpIssue/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);

    @PostMapping("/helpIssue/queryList")
    List<HelpIssueDO> queryList(@RequestBody Map<String, Object> params);
}
