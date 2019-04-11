package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.InOutItem;
import com.seamwhole.serviceerpcore.model.InOutItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InOutItemMapper {
    int countByExample(InOutItemExample example);

    int deleteByExample(InOutItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InOutItem record);

    int insertSelective(InOutItem record);

    List<InOutItem> selectByExample(InOutItemExample example);

    InOutItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InOutItem record, @Param("example") InOutItemExample example);

    int updateByExample(@Param("record") InOutItem record, @Param("example") InOutItemExample example);

    int updateByPrimaryKeySelective(InOutItem record);

    int updateByPrimaryKey(InOutItem record);
}