package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.AddressDO;

import java.util.List;
import java.util.Map;

public interface ShopAddressExtMapper {

    AddressDO getById(Integer id);

    List<AddressDO> queryList(Map<String,Object> map);
}
