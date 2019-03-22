package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.ShopCartDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCartExtMapper {

    List<ShopCartDO> queryList(@Param("userId")Integer userId,
                               @Param("goodsId") Integer goodsId,
                               @Param("productId") Integer productId,
                               @Param("checked") Boolean checked,
                               @Param("order")String order);
}
