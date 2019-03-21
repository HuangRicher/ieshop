package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.GoodsIssue;

import java.util.List;
import java.util.Map;


public interface GoodsIssueService {

    GoodsIssue queryObject(Integer id);

    List<GoodsIssue> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(GoodsIssue goods);

    void update(GoodsIssue goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
