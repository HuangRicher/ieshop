package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.HelpType;
import com.seamwhole.servicetradecore.model.HelpTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HelpTypeMapper {
    int countByExample(HelpTypeExample example);

    int deleteByExample(HelpTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HelpType record);

    int insertSelective(HelpType record);

    List<HelpType> selectByExample(HelpTypeExample example);

    HelpType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HelpType record, @Param("example") HelpTypeExample example);

    int updateByExample(@Param("record") HelpType record, @Param("example") HelpTypeExample example);

    int updateByPrimaryKeySelective(HelpType record);

    int updateByPrimaryKey(HelpType record);
}