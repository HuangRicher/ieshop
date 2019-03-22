package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopChannel;

import java.util.List;
import java.util.Map;


public interface ChannelService {

    List<ShopChannel> queryList(Map<String, Object> map);

}
