package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopChannel;
import com.seamwhole.servicetradecore.model.ShopChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopChannelMapper {
    int countByExample(ShopChannelExample example);

    int deleteByExample(ShopChannelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopChannel record);

    int insertSelective(ShopChannel record);

    List<ShopChannel> selectByExample(ShopChannelExample example);

    ShopChannel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopChannel record, @Param("example") ShopChannelExample example);

    int updateByExample(@Param("record") ShopChannel record, @Param("example") ShopChannelExample example);

    int updateByPrimaryKeySelective(ShopChannel record);

    int updateByPrimaryKey(ShopChannel record);
}