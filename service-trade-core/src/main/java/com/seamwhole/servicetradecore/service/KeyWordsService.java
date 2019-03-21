package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.KeyWords;

import java.util.List;
import java.util.Map;


public interface KeyWordsService {

    KeyWords queryObject(Integer id);

    List<KeyWords> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(KeyWords goods);

    void update(KeyWords goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<Map> hotKeywordList(Map<String, Object> map);
}
