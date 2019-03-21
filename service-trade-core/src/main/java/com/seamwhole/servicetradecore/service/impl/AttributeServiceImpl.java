package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.ShopAttributeMapper;
import com.seamwhole.servicetradecore.model.ShopAttribute;
import com.seamwhole.servicetradecore.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private ShopAttributeMapper shopAttributeMapper;


    public ShopAttribute queryObject(Integer id) {
        return shopAttributeMapper.selectByPrimaryKey(id);
    }


    public List<ShopAttribute> queryList(Map<String, Object> map) {
        //return shopAttributeMapper.queryList(map);
        return null;
    }

    public int queryTotal(Map<String, Object> map) {
        //return shopAttributeMapper.queryTotal(map);
        return 0;
    }


    public void save(ShopAttribute goods) {
        shopAttributeMapper.insertSelective(goods);
    }


    public void update(ShopAttribute goods) {
        //shopAttributeMapper.updateById(goods);
    }


    public void delete(Integer id) {
        shopAttributeMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        //shopAttributeMapper.deleteBatch(ids);
    }

}
