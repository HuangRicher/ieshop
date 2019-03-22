package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.ShopChannelMapper;
import com.seamwhole.servicetradecore.model.ShopChannel;
import com.seamwhole.servicetradecore.model.ShopChannelExample;
import com.seamwhole.servicetradecore.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ShopChannelMapper shopChannelMapper;


    public List<ShopChannel> queryList(Map<String, Object> map) {
        ShopChannelExample example=new ShopChannelExample();
        example.createCriteria().and
        return channelDao.queryList(map);
    }


}
