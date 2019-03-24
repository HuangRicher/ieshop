package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.SysSmsLog;
import com.seamwhole.servicetradecore.model.SysSmsLogExample;
import com.seamwhole.servicetradecore.model.SysSmsLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSmsLogMapper {
    int countByExample(SysSmsLogExample example);

    int deleteByExample(SysSmsLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysSmsLogWithBLOBs record);

    int insertSelective(SysSmsLogWithBLOBs record);

    List<SysSmsLogWithBLOBs> selectByExampleWithBLOBs(SysSmsLogExample example);

    List<SysSmsLog> selectByExample(SysSmsLogExample example);

    SysSmsLogWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysSmsLogWithBLOBs record, @Param("example") SysSmsLogExample example);

    int updateByExampleWithBLOBs(@Param("record") SysSmsLogWithBLOBs record, @Param("example") SysSmsLogExample example);

    int updateByExample(@Param("record") SysSmsLog record, @Param("example") SysSmsLogExample example);

    int updateByPrimaryKeySelective(SysSmsLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysSmsLogWithBLOBs record);

    int updateByPrimaryKey(SysSmsLog record);
}