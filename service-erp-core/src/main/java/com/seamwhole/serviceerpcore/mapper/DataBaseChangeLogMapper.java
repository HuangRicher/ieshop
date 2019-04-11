package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.DataBaseChangeLog;
import com.seamwhole.serviceerpcore.model.DataBaseChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataBaseChangeLogMapper {
    int countByExample(DataBaseChangeLogExample example);

    int deleteByExample(DataBaseChangeLogExample example);

    int insert(DataBaseChangeLog record);

    int insertSelective(DataBaseChangeLog record);

    List<DataBaseChangeLog> selectByExample(DataBaseChangeLogExample example);

    int updateByExampleSelective(@Param("record") DataBaseChangeLog record, @Param("example") DataBaseChangeLogExample example);

    int updateByExample(@Param("record") DataBaseChangeLog record, @Param("example") DataBaseChangeLogExample example);
}