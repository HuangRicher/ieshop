package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.DepotHead;
import com.seamwhole.serviceerpcore.model.DepotHeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepotHeadMapper {
    int countByExample(DepotHeadExample example);

    int deleteByExample(DepotHeadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DepotHead record);

    int insertSelective(DepotHead record);

    List<DepotHead> selectByExample(DepotHeadExample example);

    DepotHead selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DepotHead record, @Param("example") DepotHeadExample example);

    int updateByExample(@Param("record") DepotHead record, @Param("example") DepotHeadExample example);

    int updateByPrimaryKeySelective(DepotHead record);

    int updateByPrimaryKey(DepotHead record);
}