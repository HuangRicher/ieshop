package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.AssetName;
import com.seamwhole.serviceerpcore.model.AssetNameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetNameMapper {
    int countByExample(AssetNameExample example);

    int deleteByExample(AssetNameExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetName record);

    int insertSelective(AssetName record);

    List<AssetName> selectByExampleWithBLOBs(AssetNameExample example);

    List<AssetName> selectByExample(AssetNameExample example);

    AssetName selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssetName record, @Param("example") AssetNameExample example);

    int updateByExampleWithBLOBs(@Param("record") AssetName record, @Param("example") AssetNameExample example);

    int updateByExample(@Param("record") AssetName record, @Param("example") AssetNameExample example);

    int updateByPrimaryKeySelective(AssetName record);

    int updateByPrimaryKeyWithBLOBs(AssetName record);

    int updateByPrimaryKey(AssetName record);
}