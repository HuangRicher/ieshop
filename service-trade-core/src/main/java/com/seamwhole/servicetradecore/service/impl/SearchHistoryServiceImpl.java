package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.SearchHistoryMapper;
import com.seamwhole.servicetradecore.model.SearchHistory;
import com.seamwhole.servicetradecore.model.SearchHistoryExample;
import com.seamwhole.servicetradecore.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;




    public List<SearchHistory> queryList(Map<String, Object> map) {
        SearchHistoryExample example=new SearchHistoryExample();
        SearchHistoryExample.Criteria criteria=example.createCriteria();
        if(map.get("userId")!=null)
            criteria.andUserIdEqualTo((String) map.get("userId"));
        return searchHistoryMapper.selectByExample(example);
    }


    public void save(SearchHistory region) {
        searchHistoryMapper.insertSelective(region);
    }


    public void deleteByUserId(Integer userId) {
        SearchHistoryExample example=new SearchHistoryExample();
        example.createCriteria().andUserIdEqualTo(userId+"");
        searchHistoryMapper.deleteByExample(example);
    }
}
