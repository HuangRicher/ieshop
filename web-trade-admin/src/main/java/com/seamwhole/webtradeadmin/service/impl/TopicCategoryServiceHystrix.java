package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.TopicCategory;
import com.seamwhole.webtradeadmin.service.TopicCategoryService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TopicCategoryServiceHystrix implements TopicCategoryService {
    @Override
    public PagesInfo<TopicCategory> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public TopicCategory queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(TopicCategory topicCategory) {

    }

    @Override
    public void update(TopicCategory topicCategory) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<TopicCategory> queryList(Map<String, Object> params) {
        return null;
    }
}
