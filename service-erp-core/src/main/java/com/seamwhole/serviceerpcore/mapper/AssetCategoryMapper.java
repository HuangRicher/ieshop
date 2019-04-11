package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.AssetCategory;
import com.seamwhole.serviceerpcore.model.AssetCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetCategoryMapper {
    int countByExample(AssetCategoryExample example);

    int deleteByExample(AssetCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetCategory record);

    int insertSelective(AssetCategory record);

    List<AssetCategory> selectByExample(AssetCategoryExample example);

    AssetCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssetCategory record, @Param("example") AssetCategoryExample example);

    int updateByExample(@Param("record") AssetCategory record, @Param("example") AssetCategoryExample example);

    int updateByPrimaryKeySelective(AssetCategory record);

    int updateByPrimaryKey(AssetCategory record);
}