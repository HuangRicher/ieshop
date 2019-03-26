package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.ProductDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductExtMapper {

    ProductDO queryObject(@Param("id") Integer id);

    List<ProductDO> queryList(Map<String,Object> map);
}
