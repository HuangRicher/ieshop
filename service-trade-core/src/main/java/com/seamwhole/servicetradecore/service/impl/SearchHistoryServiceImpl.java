package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.SearchHistoryMapper;
import com.seamwhole.servicetradecore.mapper.model.SearchHistoryDO;
import com.seamwhole.servicetradecore.model.SearchHistory;
import com.seamwhole.servicetradecore.model.SearchHistoryExample;
import com.seamwhole.servicetradecore.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;



    @Override
    public List<SearchHistory> queryList(Map<String, Object> map) {
        SearchHistoryExample example=new SearchHistoryExample();
        SearchHistoryExample.Criteria criteria=example.createCriteria();
        if(map.get("userId")!=null)
            criteria.andUserIdEqualTo((String) map.get("userId"));
        return searchHistoryMapper.selectByExample(example);
    }

    @Override
    public void save(SearchHistory region) {
        searchHistoryMapper.insertSelective(region);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        SearchHistoryExample example=new SearchHistoryExample();
        example.createCriteria().andUserIdEqualTo(userId+"");
        searchHistoryMapper.deleteByExample(example);
    }

    @Override
    public PageInfo<SearchHistoryDO> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<SearchHistoryDO> page= PageHelper.startPage(pageNum,pageSize);
        SearchHistoryExample example=new SearchHistoryExample();
        searchHistoryMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public SearchHistory getById(Integer id) {
        return searchHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateById(SearchHistory searchHistory) {
        searchHistoryMapper.updateByPrimaryKeySelective(searchHistory);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        SearchHistoryExample example=new SearchHistoryExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        searchHistoryMapper.deleteByExample(example);
    }
}
