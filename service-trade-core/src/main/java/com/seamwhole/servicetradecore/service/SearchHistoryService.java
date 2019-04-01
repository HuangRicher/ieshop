package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.SearchHistoryDO;
import com.seamwhole.servicetradecore.model.SearchHistory;

import java.util.List;
import java.util.Map;


public interface SearchHistoryService {


    List<SearchHistory> queryList(Map<String, Object> map);;

    void save(SearchHistory region);

    void deleteByUserId(Integer userId);

    PageInfo<SearchHistoryDO> queryByPage(Map<String, Object> params, Integer page, Integer limit);

    SearchHistory getById(Integer id);

    void updateById(SearchHistory searchHistory);

    void deleteBatch(Integer[] ids);
}
