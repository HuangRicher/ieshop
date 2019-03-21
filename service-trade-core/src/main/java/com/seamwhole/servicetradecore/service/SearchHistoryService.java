package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.SearchHistory;

import java.util.List;
import java.util.Map;


public interface SearchHistoryService {

    SearchHistory queryObject(Integer id);

    List<SearchHistory> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SearchHistory region);

    void update(SearchHistory region);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    void deleteByUserId(Long userId);
}
