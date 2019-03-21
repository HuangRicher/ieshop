package com.seamwhole.servicefarmplan.service.impl;

import com.seamwhole.servicefarmplan.mapper.FeedMapper;
import com.seamwhole.servicefarmplan.model.Feed;
import com.seamwhole.servicefarmplan.model.FeedExample;
import com.seamwhole.servicefarmplan.service.FeedService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedMapper feedMapper;


    @Override
    public void add(String name, String constituentPart, String constituentElement) {
        Feed feed=new Feed();
        feed.setName(name);
        feed.setConstituentPart(constituentPart);
        feed.setConstituentElement(constituentElement);
        feedMapper.insertSelective(feed);
    }

    @Override
    public List<Feed> findAll() {
        FeedExample example=new FeedExample();
        return feedMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        feedMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(Integer id, String name, String constituentPart, String constituentElement) {
        Feed feed=new Feed();
        feed.setId(id);
        if(StringUtils.isNotBlank(name)){
            feed.setName(name);
        }
        if(StringUtils.isNotBlank(constituentPart)){
            feed.setConstituentPart(constituentPart);
        }
        if(StringUtils.isNotBlank(constituentElement)){
            feed.setConstituentElement(constituentElement);
        }
        feedMapper.updateByPrimaryKeySelective(feed);
    }
}
