package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.SerialNumber;
import com.seamwhole.serviceerpcore.model.SerialNumberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SerialNumberMapper {
    int countByExample(SerialNumberExample example);

    int deleteByExample(SerialNumberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SerialNumber record);

    int insertSelective(SerialNumber record);

    List<SerialNumber> selectByExample(SerialNumberExample example);

    SerialNumber selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SerialNumber record, @Param("example") SerialNumberExample example);

    int updateByExample(@Param("record") SerialNumber record, @Param("example") SerialNumberExample example);

    int updateByPrimaryKeySelective(SerialNumber record);

    int updateByPrimaryKey(SerialNumber record);
}