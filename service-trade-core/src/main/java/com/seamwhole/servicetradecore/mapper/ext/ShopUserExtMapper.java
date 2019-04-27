package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.ShopUserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShopUserExtMapper {

    List<ShopUserDO> queryList(Map<String,Object> map);

    int subUserWallet(@Param("userId") Integer userId, @Param("consumeCount") double consumeCount);
}
