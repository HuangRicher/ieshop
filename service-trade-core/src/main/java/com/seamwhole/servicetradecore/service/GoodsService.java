package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.GoodsDO;
import com.seamwhole.servicetradecore.mapper.model.ShopGoodsDO;
import com.seamwhole.servicetradecore.model.Goods;

import java.util.List;
import java.util.Map;


public interface GoodsService {

    Goods queryObject(Integer id);

    List<GoodsDO> queryList(Map<String, Object> map);

    PageInfo<GoodsDO> queryByPage(Map<String, Object> map,Integer pageNum,Integer pageSize);

    int queryTotal(Map<String, Object> map);

    void save(Goods goods);

    void update(Goods goods);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<GoodsDO> queryHotGoodsList(Map<String, Object> map);

    List<GoodsDO> queryCatalogProductList(Map<String, Object> map);

    PageInfo<GoodsDO> queryCatalogProductByPage(Map<String, Object> map,Integer pageNum,Integer pageSize);

    PageInfo<ShopGoodsDO> queryShopByPage(Map<String, Object> params, Integer page, Integer limit);

    List<ShopGoodsDO> queryShopList(Map<String, Object> params);

    int back(Integer[] ids, Long userId);

    int queryShopToTal(Map<String, Object> params);

    int enSale(Integer id, Long userId);

    int unSale(Integer id, Long userId);

    int deleteShopBatch(Integer[] ids, Long userId);

    int deleteShopGoods(Integer id, Long userId);

    int saveShopGoods(Goods goods, Long userId, Long deptId);
}
