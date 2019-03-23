package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.KeyWords;

import java.util.List;
import java.util.Map;


public interface KeyWordsService {

    List<KeyWords> queryList(Map<String, Object> map);

    List<KeyWords> hotKeywordList(Map<String, Object> map);
}
