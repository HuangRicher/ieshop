package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.GoodsSpecificationDO;
import com.seamwhole.servicetradecore.mapper.model.ShopGoodsSpecificationDO;

import java.util.List;
import java.util.Map;

public interface GoodsSpecificationExtMapper {

    List<GoodsSpecificationDO> queryList(Map<String, Object> map);

    List<ShopGoodsSpecificationDO> queryShopList(Map<String, Object> map);
}