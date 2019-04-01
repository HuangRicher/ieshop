package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopShipping;
import com.seamwhole.servicetradecore.model.ShopShippingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopShippingMapper {
    int countByExample(ShopShippingExample example);

    int deleteByExample(ShopShippingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopShipping record);

    int insertSelective(ShopShipping record);

    List<ShopShipping> selectByExample(ShopShippingExample example);

    ShopShipping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopShipping record, @Param("example") ShopShippingExample example);

    int updateByExample(@Param("record") ShopShipping record, @Param("example") ShopShippingExample example);

    int updateByPrimaryKeySelective(ShopShipping record);

    int updateByPrimaryKey(ShopShipping record);
}