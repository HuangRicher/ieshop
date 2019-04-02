package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.KeyWordsMapper;
import com.seamwhole.servicetradecore.model.KeyWords;
import com.seamwhole.servicetradecore.model.KeyWordsExample;
import com.seamwhole.servicetradecore.service.KeyWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public void deleteBatch(Integer[] ids) {
        KeyWordsExample example=new KeyWordsExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        keyWordsMapper.deleteByExample(example);
    }

    @Override
    public void updateById(KeyWords keywords) {
        keyWordsMapper.updateByPrimaryKeySelective(keywords);
    }

    @Override
    public void save(KeyWords keywords) {
        keyWordsMapper.insertSelective(keywords);
    }

    @Override
    public KeyWords getById(Integer id) {
        return keyWordsMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<KeyWords> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        KeyWordsExample example=new KeyWordsExample();
        if(params.get("keyword")!=null)
            example.createCriteria().andKeywordLike("%"+params.get("keyword")+"%");
        Page<KeyWords> page=PageHelper.startPage(pageNum,pageSize);
        keyWordsMapper.selectByExample(example);
        return page.toPageInfo();
    }
}
