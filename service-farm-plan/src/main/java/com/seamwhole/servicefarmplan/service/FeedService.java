package com.seamwhole.servicefarmplan.service;

import com.seamwhole.servicefarmplan.model.Feed;

import java.util.List;

public interface FeedService {

    void add(String name,String constituentPart,String constituentElement);

    List<Feed> findAll();

    void delete(Integer id);

    void updateById(Integer id,String name,String constituentPart,String constituentElement);
}
