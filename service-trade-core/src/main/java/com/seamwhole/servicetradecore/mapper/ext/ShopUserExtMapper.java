package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.ShopUserDO;

import java.util.List;
import java.util.Map;

public interface ShopUserExtMapper {

    List<ShopUserDO> queryList(Map<String,Object> map);
}
