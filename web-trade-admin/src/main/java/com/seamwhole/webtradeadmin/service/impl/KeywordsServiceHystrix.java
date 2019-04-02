package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.KeyWords;
import com.seamwhole.webtradeadmin.service.KeywordsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class KeywordsServiceHystrix implements KeywordsService {
    @Override
    public PagesInfo<KeyWords> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public KeyWords queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(KeyWords keywords) {

    }

    @Override
    public void update(KeyWords keywords) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<KeyWords> queryList(Map<String, Object> params) {
        return null;
    }
}
