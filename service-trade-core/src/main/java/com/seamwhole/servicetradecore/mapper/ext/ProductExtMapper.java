package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.ProductDO;
import org.apache.ibatis.annotations.Param;

public interface ProductExtMapper {

    ProductDO queryObject(@Param("id") Integer id);
}
