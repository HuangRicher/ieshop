package com.seamwhole.webtradeadmin.service;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Goods;
import com.seamwhole.webtradeadmin.info.ShopGoodsDO;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    PagesInfo<ShopGoodsDO> queryByPage(Map<String, Object> params);

    Goods queryObject(Integer id);

    void save(Goods goods);

    void update(Goods goods);

    void deleteBatch(Integer[] ids);

    List<ShopGoodsDO> queryList(Map<String, Object> params);

    void back(Integer[] ids);

    int queryTotal(Map<String, Object> params);

    void enSale(Integer id);

    void unSale(Integer id);
}
