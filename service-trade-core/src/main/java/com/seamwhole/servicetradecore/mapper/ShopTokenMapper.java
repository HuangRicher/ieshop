package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopToken;
import com.seamwhole.servicetradecore.model.ShopTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopTokenMapper {
    int countByExample(ShopTokenExample example);

    int deleteByExample(ShopTokenExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(ShopToken record);

    int insertSelective(ShopToken record);

    List<ShopToken> selectByExample(ShopTokenExample example);

    ShopToken selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") ShopToken record, @Param("example") ShopTokenExample example);

    int updateByExample(@Param("record") ShopToken record, @Param("example") ShopTokenExample example);

    int updateByPrimaryKeySelective(ShopToken record);

    int updateByPrimaryKey(ShopToken record);
}