package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.GoodsAttribute;
import com.seamwhole.servicetradecore.model.GoodsAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsAttributeMapper {
    int countByExample(GoodsAttributeExample example);

    int deleteByExample(GoodsAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttribute record);

    int insertSelective(GoodsAttribute record);

    List<GoodsAttribute> selectByExampleWithBLOBs(GoodsAttributeExample example);

    List<GoodsAttribute> selectByExample(GoodsAttributeExample example);

    GoodsAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsAttribute record, @Param("example") GoodsAttributeExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsAttribute record, @Param("example") GoodsAttributeExample example);

    int updateByExample(@Param("record") GoodsAttribute record, @Param("example") GoodsAttributeExample example);

    int updateByPrimaryKeySelective(GoodsAttribute record);

    int updateByPrimaryKeyWithBLOBs(GoodsAttribute record);

    int updateByPrimaryKey(GoodsAttribute record);
}