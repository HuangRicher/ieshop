package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopAddress;
import com.seamwhole.servicetradecore.model.ShopAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopAddressMapper {
    int countByExample(ShopAddressExample example);

    int deleteByExample(ShopAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopAddress record);

    int insertSelective(ShopAddress record);

    List<ShopAddress> selectByExample(ShopAddressExample example);

    ShopAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopAddress record, @Param("example") ShopAddressExample example);

    int updateByExample(@Param("record") ShopAddress record, @Param("example") ShopAddressExample example);

    int updateByPrimaryKeySelective(ShopAddress record);

    int updateByPrimaryKey(ShopAddress record);
}