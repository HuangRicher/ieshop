package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopAttribute;
import com.seamwhole.servicetradecore.model.ShopAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopAttributeMapper {
    int countByExample(ShopAttributeExample example);

    int deleteByExample(ShopAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopAttribute record);

    int insertSelective(ShopAttribute record);

    List<ShopAttribute> selectByExampleWithBLOBs(ShopAttributeExample example);

    List<ShopAttribute> selectByExample(ShopAttributeExample example);

    ShopAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopAttribute record, @Param("example") ShopAttributeExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopAttribute record, @Param("example") ShopAttributeExample example);

    int updateByExample(@Param("record") ShopAttribute record, @Param("example") ShopAttributeExample example);

    int updateByPrimaryKeySelective(ShopAttribute record);

    int updateByPrimaryKeyWithBLOBs(ShopAttribute record);

    int updateByPrimaryKey(ShopAttribute record);
}