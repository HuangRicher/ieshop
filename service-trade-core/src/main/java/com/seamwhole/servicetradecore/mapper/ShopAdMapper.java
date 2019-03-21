package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopAd;
import com.seamwhole.servicetradecore.model.ShopAdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopAdMapper {
    int countByExample(ShopAdExample example);

    int deleteByExample(ShopAdExample example);

    int deleteByPrimaryKey(Short id);

    int insert(ShopAd record);

    int insertSelective(ShopAd record);

    List<ShopAd> selectByExampleWithBLOBs(ShopAdExample example);

    List<ShopAd> selectByExample(ShopAdExample example);

    ShopAd selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") ShopAd record, @Param("example") ShopAdExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopAd record, @Param("example") ShopAdExample example);

    int updateByExample(@Param("record") ShopAd record, @Param("example") ShopAdExample example);

    int updateByPrimaryKeySelective(ShopAd record);

    int updateByPrimaryKeyWithBLOBs(ShopAd record);

    int updateByPrimaryKey(ShopAd record);
}