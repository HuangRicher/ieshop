package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SearchHistory;
import com.seamwhole.webtradeadmin.info.SearchHistoryDO;
import com.seamwhole.webtradeadmin.service.SearchHistoryService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SearchHistoryServiceHystrix implements SearchHistoryService {
    @Override
    public PagesInfo<SearchHistoryDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public SearchHistory queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(SearchHistory searchHistory) {

    }

    @Override
    public void update(SearchHistory searchHistory) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }
}
