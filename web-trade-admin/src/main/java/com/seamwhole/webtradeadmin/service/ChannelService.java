package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopChannel;

import java.util.List;
import java.util.Map;

public interface ChannelService {
    PagesInfo<ShopChannel> queryByPage(Map<String, Object> params);

    ShopChannel queryObject(Integer id);

    void save(ShopChannel channel);

    void update(ShopChannel channel);

    void deleteBatch(Integer[] ids);

    List<ShopChannel> queryList(Map<String, Object> params);
}
