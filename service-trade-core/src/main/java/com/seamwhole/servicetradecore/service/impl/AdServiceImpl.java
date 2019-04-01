package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ShopAdMapper;
import com.seamwhole.servicetradecore.mapper.ext.ShopAdExtMapper;
import com.seamwhole.servicetradecore.mapper.model.ShopAdDO;
import com.seamwhole.servicetradecore.model.ShopAd;
import com.seamwhole.servicetradecore.model.ShopAdExample;
import com.seamwhole.servicetradecore.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private ShopAdMapper shopAdMapper;
    @Autowired
    private ShopAdExtMapper shopAdExtMapper;


    public List<ShopAd> queryByPositionId(Integer positionId) {
        ShopAdExample example=new ShopAdExample();
        example.createCriteria().andAdPositionIdEqualTo(positionId).andEnabledEqualTo(1);
        return shopAdMapper.selectByExample(example);
    }

    @Override
    public List<ShopAdDO> queryShopAdList(Map<String, Object> params) {
        return shopAdExtMapper.queryShopAdList(params);
    }

    @Override
    public PageInfo<ShopAdDO> queryShopAdByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<ShopAdDO> page= PageHelper.startPage(pageNum,pageSize);
        shopAdExtMapper.queryShopAdList(params);
        return page.toPageInfo();
    }

    @Override
    public void save(ShopAd shopAd) {
        shopAdMapper.insertSelective(shopAd);
    }

    @Override
    public void updateById(ShopAd shopAd) {
        shopAdMapper.updateByPrimaryKeyWithBLOBs(shopAd);
    }

    @Override
    public ShopAd getById(Integer id) {
        return shopAdMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        ShopAdExample example=new ShopAdExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        shopAdMapper.deleteByExample(example);
    }
}
