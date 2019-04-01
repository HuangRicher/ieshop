package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.ShopCartDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShopCartExtMapper {

    List<ShopCartDO> queryList(@Param("userId")Integer userId,
                               @Param("sessionId")String sessionId,
                               @Param("goodsId") Integer goodsId,
                               @Param("productId") Integer productId,
                               @Param("checked") Integer checked,
                               @Param("order")String order);

    List<ShopCartDO> queryShopUserList(Map<String,Object> map);
}
