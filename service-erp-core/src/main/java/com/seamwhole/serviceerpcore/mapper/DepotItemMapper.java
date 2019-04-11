package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.DepotItem;
import com.seamwhole.serviceerpcore.model.DepotItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepotItemMapper {
    int countByExample(DepotItemExample example);

    int deleteByExample(DepotItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DepotItem record);

    int insertSelective(DepotItem record);

    List<DepotItem> selectByExample(DepotItemExample example);

    DepotItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DepotItem record, @Param("example") DepotItemExample example);

    int updateByExample(@Param("record") DepotItem record, @Param("example") DepotItemExample example);

    int updateByPrimaryKeySelective(DepotItem record);

    int updateByPrimaryKey(DepotItem record);
}