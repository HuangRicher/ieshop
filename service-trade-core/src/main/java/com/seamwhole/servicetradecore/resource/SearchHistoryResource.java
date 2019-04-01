package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.SearchHistoryDO;
import com.seamwhole.servicetradecore.model.SearchHistory;
import com.seamwhole.servicetradecore.service.SearchHistoryService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/searchHistory")
public class SearchHistoryResource {

    @Autowired
    private SearchHistoryService searchHistoryService;


    @PostMapping("/queryByPage")
    public PagesInfo<SearchHistoryDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<SearchHistoryDO> pageInfo=searchHistoryService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<SearchHistoryDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public SearchHistory queryObject(@PathVariable("id") Integer id){
        return searchHistoryService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody SearchHistory searchHistory){
        searchHistoryService.save(searchHistory);
    }

    @PostMapping("/update")
    public void update(@RequestBody SearchHistory searchHistory){
        searchHistoryService.updateById(searchHistory);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        searchHistoryService.deleteBatch(ids);
    }
}
