package com.seamwhole.servicefarmplan.mapper;

import com.seamwhole.servicefarmplan.model.PlanRequirementsItem;
import com.seamwhole.servicefarmplan.model.PlanRequirementsItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlanRequirementsItemMapper {
    int countByExample(PlanRequirementsItemExample example);

    int deleteByExample(PlanRequirementsItemExample example);

    int deleteByPrimaryKey(String itemId);

    int insert(PlanRequirementsItem record);

    int insertSelective(PlanRequirementsItem record);

    List<PlanRequirementsItem> selectByExample(PlanRequirementsItemExample example);

    PlanRequirementsItem selectByPrimaryKey(String itemId);

    int updateByExampleSelective(@Param("record") PlanRequirementsItem record, @Param("example") PlanRequirementsItemExample example);

    int updateByExample(@Param("record") PlanRequirementsItem record, @Param("example") PlanRequirementsItemExample example);

    int updateByPrimaryKeySelective(PlanRequirementsItem record);

    int updateByPrimaryKey(PlanRequirementsItem record);
}