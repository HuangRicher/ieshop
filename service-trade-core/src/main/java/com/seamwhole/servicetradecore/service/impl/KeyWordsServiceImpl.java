package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.PageHelper;
import com.seamwhole.servicetradecore.mapper.KeyWordsMapper;
import com.seamwhole.servicetradecore.model.KeyWords;
import com.seamwhole.servicetradecore.model.KeyWordsExample;
import com.seamwhole.servicetradecore.service.KeyWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class KeyWordsServiceImpl implements KeyWordsService {

    @Autowired
    private KeyWordsMapper keyWordsMapper;


    public List<KeyWords> queryList(Map<String, Object> map) {
        KeyWordsExample example=new KeyWordsExample();
        if(map.get("keyword")!=null)
            example.createCriteria().andKeywordLike("%"+(String)map.get("keyword")+"%");
        return keyWordsMapper.selectByExample(example);
    }


    public List<KeyWords> hotKeywordList(Map<String, Object> map) {
        KeyWordsExample example=new KeyWordsExample();
        PageHelper.startPage(1,10);
        return keyWordsMapper.selectByExample(example);
    }
}
