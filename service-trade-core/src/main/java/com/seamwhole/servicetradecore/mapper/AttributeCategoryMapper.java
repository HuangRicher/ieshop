package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.AttributeCategory;
import com.seamwhole.servicetradecore.model.AttributeCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttributeCategoryMapper {
    int countByExample(AttributeCategoryExample example);

    int deleteByExample(AttributeCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttributeCategory record);

    int insertSelective(AttributeCategory record);

    List<AttributeCategory> selectByExample(AttributeCategoryExample example);

    AttributeCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttributeCategory record, @Param("example") AttributeCategoryExample example);

    int updateByExample(@Param("record") AttributeCategory record, @Param("example") AttributeCategoryExample example);

    int updateByPrimaryKeySelective(AttributeCategory record);

    int updateByPrimaryKey(AttributeCategory record);
}