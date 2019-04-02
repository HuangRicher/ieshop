package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.OrderDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderExtMapper {

    OrderDO queryObject(@Param("id") Integer id);

    OrderDO queryShopOrderObject(@Param("id") Integer id);

    List<OrderDO> queryShopOrderList(Map<String,Object> map);
}
