package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.SysMacro;
import com.seamwhole.servicetradecore.model.SysMacroExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMacroMapper {
    int countByExample(SysMacroExample example);

    int deleteByExample(SysMacroExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMacro record);

    int insertSelective(SysMacro record);

    List<SysMacro> selectByExample(SysMacroExample example);

    SysMacro selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMacro record, @Param("example") SysMacroExample example);

    int updateByExample(@Param("record") SysMacro record, @Param("example") SysMacroExample example);

    int updateByPrimaryKeySelective(SysMacro record);

    int updateByPrimaryKey(SysMacro record);
}