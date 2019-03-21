package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopCart;
import com.seamwhole.servicetradecore.model.ShopCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopCartMapper {
    int countByExample(ShopCartExample example);

    int deleteByExample(ShopCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopCart record);

    int insertSelective(ShopCart record);

    List<ShopCart> selectByExampleWithBLOBs(ShopCartExample example);

    List<ShopCart> selectByExample(ShopCartExample example);

    ShopCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopCart record, @Param("example") ShopCartExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopCart record, @Param("example") ShopCartExample example);

    int updateByExample(@Param("record") ShopCart record, @Param("example") ShopCartExample example);

    int updateByPrimaryKeySelective(ShopCart record);

    int updateByPrimaryKeyWithBLOBs(ShopCart record);

    int updateByPrimaryKey(ShopCart record);
}