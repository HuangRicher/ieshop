package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopAdDO;
import com.seamwhole.servicetradecore.model.ShopAd;

import java.util.List;
import java.util.Map;


public interface AdService {

    List<ShopAd> queryByPositionId(Integer positionId);

    List<ShopAdDO> queryShopAdList(Map<String,Object> params);

    PageInfo<ShopAdDO> queryShopAdByPage(Map<String,Object> params,Integer pageNum,Integer pageSize);

    void save(ShopAd shopAd);

    void updateById(ShopAd shopAd);

    ShopAd getById(Integer id);

    void deleteBatch(Integer[] ids);
}
