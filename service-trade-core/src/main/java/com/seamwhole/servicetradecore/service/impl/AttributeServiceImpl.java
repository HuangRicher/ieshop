package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ShopAttributeMapper;
import com.seamwhole.servicetradecore.mapper.ext.ShopAttributeExpMapper;
import com.seamwhole.servicetradecore.mapper.model.ShopAttributeDO;
import com.seamwhole.servicetradecore.model.ShopAttribute;
import com.seamwhole.servicetradecore.model.ShopAttributeExample;
import com.seamwhole.servicetradecore.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private ShopAttributeMapper shopAttributeMapper;

    @Autowired
    private ShopAttributeExpMapper shopAttributeExpMapper;



    public ShopAttribute queryObject(Integer id) {
        return shopAttributeMapper.selectByPrimaryKey(id);
    }


    public List<ShopAttribute> queryList(Map<String, Object> map) {
        return shopAttributeExpMapper.queryList(map);
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
        shopAttributeMapper.updateByPrimaryKeyWithBLOBs(goods);
    }


    public void delete(Integer id) {
        shopAttributeMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        ShopAttributeExample example=new ShopAttributeExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        shopAttributeMapper.deleteByExample(example);
        //shopAttributeMapper.deleteBatch(ids);
    }

    @Override
    public PageInfo<ShopAttributeDO> queryShopAdByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<ShopAttributeDO> page= PageHelper.startPage(pageNum,pageSize);
        shopAttributeExpMapper.queryShopList(params);
        return page.toPageInfo();
    }

    @Override
    public List<ShopAttributeDO> queryShopList(Map<String, Object> params) {
        return shopAttributeExpMapper.queryShopList(params);
    }
}
