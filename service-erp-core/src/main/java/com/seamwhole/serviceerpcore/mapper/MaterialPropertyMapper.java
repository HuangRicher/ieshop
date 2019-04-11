package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.MaterialProperty;
import com.seamwhole.serviceerpcore.model.MaterialPropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialPropertyMapper {
    int countByExample(MaterialPropertyExample example);

    int deleteByExample(MaterialPropertyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialProperty record);

    int insertSelective(MaterialProperty record);

    List<MaterialProperty> selectByExample(MaterialPropertyExample example);

    MaterialProperty selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MaterialProperty record, @Param("example") MaterialPropertyExample example);

    int updateByExample(@Param("record") MaterialProperty record, @Param("example") MaterialPropertyExample example);

    int updateByPrimaryKeySelective(MaterialProperty record);

    int updateByPrimaryKey(MaterialProperty record);
}