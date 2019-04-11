package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.MaterialCategory;
import com.seamwhole.serviceerpcore.model.MaterialCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialCategoryMapper {
    int countByExample(MaterialCategoryExample example);

    int deleteByExample(MaterialCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialCategory record);

    int insertSelective(MaterialCategory record);

    List<MaterialCategory> selectByExample(MaterialCategoryExample example);

    MaterialCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MaterialCategory record, @Param("example") MaterialCategoryExample example);

    int updateByExample(@Param("record") MaterialCategory record, @Param("example") MaterialCategoryExample example);

    int updateByPrimaryKeySelective(MaterialCategory record);

    int updateByPrimaryKey(MaterialCategory record);
}