package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.ShopAdDO;

import java.util.List;
import java.util.Map;

public interface ShopAdExtMapper {

    List<ShopAdDO> queryShopAdList(Map<String,Object> map);
}
