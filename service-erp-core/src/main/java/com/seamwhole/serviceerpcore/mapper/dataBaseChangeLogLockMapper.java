package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.dataBaseChangeLogLock;
import com.seamwhole.serviceerpcore.model.dataBaseChangeLogLockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface dataBaseChangeLogLockMapper {
    int countByExample(dataBaseChangeLogLockExample example);

    int deleteByExample(dataBaseChangeLogLockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(dataBaseChangeLogLock record);

    int insertSelective(dataBaseChangeLogLock record);

    List<dataBaseChangeLogLock> selectByExample(dataBaseChangeLogLockExample example);

    dataBaseChangeLogLock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") dataBaseChangeLogLock record, @Param("example") dataBaseChangeLogLockExample example);

    int updateByExample(@Param("record") dataBaseChangeLogLock record, @Param("example") dataBaseChangeLogLockExample example);

    int updateByPrimaryKeySelective(dataBaseChangeLogLock record);

    int updateByPrimaryKey(dataBaseChangeLogLock record);
}