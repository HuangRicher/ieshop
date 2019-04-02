package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopChannel;
import com.seamwhole.webtradeadmin.service.ChannelService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ChannelServiceHystrix implements ChannelService {
    @Override
    public PagesInfo<ShopChannel> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopChannel queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopChannel channel) {

    }

    @Override
    public void update(ShopChannel channel) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<ShopChannel> queryList(Map<String, Object> params) {
        return null;
    }
}
