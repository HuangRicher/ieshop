package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopTopic;
import com.seamwhole.servicetradecore.model.ShopTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopTopicMapper {
    int countByExample(ShopTopicExample example);

    int deleteByExample(ShopTopicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopTopic record);

    int insertSelective(ShopTopic record);

    List<ShopTopic> selectByExampleWithBLOBs(ShopTopicExample example);

    List<ShopTopic> selectByExample(ShopTopicExample example);

    ShopTopic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopTopic record, @Param("example") ShopTopicExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopTopic record, @Param("example") ShopTopicExample example);

    int updateByExample(@Param("record") ShopTopic record, @Param("example") ShopTopicExample example);

    int updateByPrimaryKeySelective(ShopTopic record);

    int updateByPrimaryKeyWithBLOBs(ShopTopic record);

    int updateByPrimaryKey(ShopTopic record);
}