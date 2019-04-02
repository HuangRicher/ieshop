package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.KeyWords;

import java.util.List;
import java.util.Map;


public interface KeyWordsService {

    List<KeyWords> queryList(Map<String, Object> map);

    List<KeyWords> hotKeywordList(Map<String, Object> map);

    void deleteBatch(Integer[] ids);

    void updateById(KeyWords keywords);

    void save(KeyWords keywords);

    KeyWords getById(Integer id);

    PageInfo<KeyWords> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);
}
