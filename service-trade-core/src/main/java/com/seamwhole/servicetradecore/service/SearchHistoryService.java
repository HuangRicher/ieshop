package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.SearchHistory;

import java.util.List;
import java.util.Map;


public interface SearchHistoryService {


    List<SearchHistory> queryList(Map<String, Object> map);;

    void save(SearchHistory region);

    void deleteByUserId(Integer userId);
}
