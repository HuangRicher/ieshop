package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.Asset;
import com.seamwhole.serviceerpcore.model.AssetExample;
import com.seamwhole.serviceerpcore.model.AssetWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssetMapper {
    int countByExample(AssetExample example);

    int deleteByExample(AssetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetWithBLOBs record);

    int insertSelective(AssetWithBLOBs record);

    List<AssetWithBLOBs> selectByExampleWithBLOBs(AssetExample example);

    List<Asset> selectByExample(AssetExample example);

    AssetWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssetWithBLOBs record, @Param("example") AssetExample example);

    int updateByExampleWithBLOBs(@Param("record") AssetWithBLOBs record, @Param("example") AssetExample example);

    int updateByExample(@Param("record") Asset record, @Param("example") AssetExample example);

    int updateByPrimaryKeySelective(AssetWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AssetWithBLOBs record);

    int updateByPrimaryKey(Asset record);
}