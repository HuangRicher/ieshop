package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.ShopAttributeDO;
import com.seamwhole.servicetradecore.model.ShopAttribute;

import java.util.List;
import java.util.Map;

public interface ShopAttributeExpMapper {

    List<ShopAttributeDO> queryShopList(Map<String,Object> map);

    List<ShopAttribute> queryList(Map<String,Object> map);
}
