package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SearchHistory;
import com.seamwhole.webtradeadmin.info.SearchHistoryDO;
import com.seamwhole.webtradeadmin.service.SearchHistoryService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("searchhistory")
public class SearchHistoryController {
    @Autowired
    private SearchHistoryService searchHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("searchhistory:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<SearchHistoryDO> page=searchHistoryService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("searchhistory:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        SearchHistory searchHistory = searchHistoryService.queryObject(id);
        return ResponseObject.ok().put("searchHistory", searchHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("searchhistory:save")
    public ResponseObject save(@RequestBody SearchHistory searchHistory) {
        searchHistoryService.save(searchHistory);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("searchhistory:update")
    public ResponseObject update(@RequestBody SearchHistory searchHistory) {
        searchHistoryService.update(searchHistory);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("searchhistory:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        searchHistoryService.deleteBatch(ids);
        return ResponseObject.ok();
    }

}
