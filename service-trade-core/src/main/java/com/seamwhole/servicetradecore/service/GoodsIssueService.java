package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.GoodsIssue;

import java.util.List;
import java.util.Map;


public interface GoodsIssueService {

    List<GoodsIssue> queryList(Map<String, Object> map);

    PageInfo<GoodsIssue> queryByPage(Map<String, Object> params, Integer page, Integer limit);

    GoodsIssue getById(Integer id);

    void save(GoodsIssue goodsIssue);

    void updateById(GoodsIssue goodsIssue);

    void deleteBatch(Integer[] ids);
}
