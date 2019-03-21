package com.seamwhole.servicefarmplan.mapper;

import com.seamwhole.servicefarmplan.model.PlanRequirements;
import com.seamwhole.servicefarmplan.model.PlanRequirementsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlanRequirementsMapper {
    int countByExample(PlanRequirementsExample example);

    int deleteByExample(PlanRequirementsExample example);

    int deleteByPrimaryKey(String id);

    int insert(PlanRequirements record);

    int insertSelective(PlanRequirements record);

    List<PlanRequirements> selectByExample(PlanRequirementsExample example);

    PlanRequirements selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PlanRequirements record, @Param("example") PlanRequirementsExample example);

    int updateByExample(@Param("record") PlanRequirements record, @Param("example") PlanRequirementsExample example);

    int updateByPrimaryKeySelective(PlanRequirements record);

    int updateByPrimaryKey(PlanRequirements record);
}