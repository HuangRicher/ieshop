package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.CollectDO;
import com.seamwhole.servicetradecore.mapper.model.ShopCollectDO;
import com.seamwhole.servicetradecore.model.ShopCollect;

import java.util.List;
import java.util.Map;


public interface CollectService {

    ShopCollect queryObject(Integer id);

    List<CollectDO> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int save(ShopCollect collect);

    void update(ShopCollect collect);

    int delete(Integer id);

    void deleteBatch(Integer[] ids);

    PageInfo<ShopCollectDO> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);
}
