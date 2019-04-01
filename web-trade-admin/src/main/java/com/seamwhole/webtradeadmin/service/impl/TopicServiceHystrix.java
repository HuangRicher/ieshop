package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopTopic;
import com.seamwhole.webtradeadmin.service.TopicService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TopicServiceHystrix implements TopicService {
    @Override
    public PagesInfo<ShopTopic> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopTopic queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopTopic topic) {

    }

    @Override
    public void update(ShopTopic topic) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<ShopTopic> queryList(Map<String, Object> params) {
        return null;
    }
}
