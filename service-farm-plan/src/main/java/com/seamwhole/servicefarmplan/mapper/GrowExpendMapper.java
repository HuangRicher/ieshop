package com.seamwhole.servicefarmplan.mapper;

import com.seamwhole.servicefarmplan.model.GrowExpend;
import com.seamwhole.servicefarmplan.model.GrowExpendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GrowExpendMapper {
    int countByExample(GrowExpendExample example);

    int deleteByExample(GrowExpendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GrowExpend record);

    int insertSelective(GrowExpend record);

    List<GrowExpend> selectByExample(GrowExpendExample example);

    GrowExpend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GrowExpend record, @Param("example") GrowExpendExample example);

    int updateByExample(@Param("record") GrowExpend record, @Param("example") GrowExpendExample example);

    int updateByPrimaryKeySelective(GrowExpend record);

    int updateByPrimaryKey(GrowExpend record);
}