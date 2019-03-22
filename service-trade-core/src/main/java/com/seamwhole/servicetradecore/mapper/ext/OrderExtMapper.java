package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.OrderDO;
import org.apache.ibatis.annotations.Param;

public interface OrderExtMapper {

    OrderDO queryObject(@Param("id") Integer id);
}
