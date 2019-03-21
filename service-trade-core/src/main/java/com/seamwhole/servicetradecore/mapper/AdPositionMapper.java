package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.AdPosition;
import com.seamwhole.servicetradecore.model.AdPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdPositionMapper {
    int countByExample(AdPositionExample example);

    int deleteByExample(AdPositionExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(AdPosition record);

    int insertSelective(AdPosition record);

    List<AdPosition> selectByExample(AdPositionExample example);

    AdPosition selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") AdPosition record, @Param("example") AdPositionExample example);

    int updateByExample(@Param("record") AdPosition record, @Param("example") AdPositionExample example);

    int updateByPrimaryKeySelective(AdPosition record);

    int updateByPrimaryKey(AdPosition record);
}