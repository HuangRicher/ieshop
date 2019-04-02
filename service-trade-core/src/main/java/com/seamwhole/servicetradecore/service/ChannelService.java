package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ShopChannel;

import java.util.List;
import java.util.Map;


public interface ChannelService {

    List<ShopChannel> queryList(Map<String, Object> map);

    PageInfo<ShopChannel> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);

    ShopChannel getById(Integer id);

    void save(ShopChannel channel);

    void update(ShopChannel channel);

    void deleteBatch(Integer[] ids);
}
