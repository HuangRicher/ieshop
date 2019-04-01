package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.config.FeignConfig;
import com.seamwhole.webtradeadmin.info.SearchHistory;
import com.seamwhole.webtradeadmin.info.SearchHistoryDO;
import com.seamwhole.webtradeadmin.service.impl.SearchHistoryServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(serviceId = "trade-core-service" ,configuration = FeignConfig.class,fallback = SearchHistoryServiceHystrix.class)
public interface SearchHistoryService {


    @PostMapping("/searchHistory/queryByPage")
    PagesInfo<SearchHistoryDO> queryByPage(@RequestBody Map<String, Object> params);

    @GetMapping("/searchHistory/queryObject/{id}")
    SearchHistory queryObject(@PathVariable("id") Integer id);

    @PostMapping("/searchHistory/save")
    void save(@RequestBody SearchHistory searchHistory);

    @PostMapping("/searchHistory/update")
    void update(@RequestBody SearchHistory searchHistory);

    @PostMapping("/searchHistory/deleteBatch")
    void deleteBatch(@RequestBody Integer[] ids);
}
