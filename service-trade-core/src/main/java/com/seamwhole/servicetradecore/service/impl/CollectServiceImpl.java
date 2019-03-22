package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.ShopCollectMapper;
import com.seamwhole.servicetradecore.mapper.ext.ShopCollectExtMapper;
import com.seamwhole.servicetradecore.mapper.model.CollectDO;
import com.seamwhole.servicetradecore.model.ShopCollect;
import com.seamwhole.servicetradecore.model.ShopCollectExample;
import com.seamwhole.servicetradecore.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private ShopCollectMapper shopCollectMapper;

    @Autowired
    private ShopCollectExtMapper shopCollectExtMapper;



    public ShopCollect queryObject(Integer id) {
        return shopCollectMapper.selectByPrimaryKey(id);
    }


    public List<CollectDO> queryList(Map<String, Object> map) {
        return shopCollectExtMapper.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        ShopCollectExample example=new ShopCollectExample();
        ShopCollectExample.Criteria criteria=example.createCriteria();
        if(map.get("userId")!=null)
            criteria.andUserIdEqualTo((int)map.get("userId"));
        if(map.get("typeId")!=null)
            criteria.andTypeIdEqualTo((int)map.get("typeId"));
        if(map.get("valueId")!=null)
            criteria.andValueIdEqualTo((int)map.get("valueId"));
        return shopCollectMapper.countByExample(example);
    }


    public int save(ShopCollect collect) {
        return shopCollectMapper.insertSelective(collect);
    }


    public void update(ShopCollect collect) {
        shopCollectMapper.updateByPrimaryKeySelective(collect);
    }


    public int delete(Integer id) {
        return shopCollectMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        ShopCollectExample example=new ShopCollectExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        shopCollectMapper.deleteByExample(example);
    }

}
