package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.CollectDO;
import com.seamwhole.servicetradecore.mapper.model.ShopCollectDO;

import java.util.List;
import java.util.Map;

public interface ShopCollectExtMapper {

    List<CollectDO> queryList(Map<String, Object> map);

    List<ShopCollectDO> queryShopCollectList(Map<String, Object> map);
}
