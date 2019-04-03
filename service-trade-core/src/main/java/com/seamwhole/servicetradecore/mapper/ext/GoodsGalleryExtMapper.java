package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.GoodsGalleryDO;

import java.util.List;
import java.util.Map;

public interface GoodsGalleryExtMapper {

    List<GoodsGalleryDO> queryShopList(Map<String,Object> map);
}
