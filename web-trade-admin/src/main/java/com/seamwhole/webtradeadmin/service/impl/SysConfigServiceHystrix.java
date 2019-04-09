package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysConfig;
import com.seamwhole.webtradeadmin.service.SysConfigService;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class SysConfigServiceHystrix implements SysConfigService {
    @Override
    public PagesInfo<SysConfig> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public SysConfig queryObject(Long id) {
        return null;
    }

    @Override
    public void save(SysConfig config) {

    }

    @Override
    public void update(SysConfig config) {

    }

    @Override
    public void deleteBatch(Long[] ids) {

    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        return null;
    }

    @Override
    public void updateValueByKey(String key, String s) {

    }
}
