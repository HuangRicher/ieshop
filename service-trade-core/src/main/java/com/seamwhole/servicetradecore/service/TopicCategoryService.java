package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.TopicCategory;

import java.util.List;
import java.util.Map;

public interface TopicCategoryService {
    PageInfo<TopicCategory> queryByPage(Map<String, Object> params, Integer page, Integer limit);

    TopicCategory getById(Integer id);

    void save(TopicCategory topicCategory);

    void updateById(TopicCategory topicCategory);

    void deleteBatch(Integer[] ids);

    List<TopicCategory> queryList(Map<String, Object> params);
}
