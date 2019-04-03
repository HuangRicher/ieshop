package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.GoodsDO;
import com.seamwhole.servicetradecore.mapper.model.ShopGoodsDO;

import java.util.List;
import java.util.Map;

public interface GoodsExtMapper {

    List<GoodsDO> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryShopTotal(Map<String, Object> map);

    List<GoodsDO> queryHotGoodsList(Map<String, Object> map);

    List<GoodsDO> queryCatalogProductList(Map<String, Object> map);

    List<ShopGoodsDO> queryShopGoodsList(Map<String, Object> map);

    int queryShopMaxId();
}
