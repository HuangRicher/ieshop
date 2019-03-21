package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopBrand;
import com.seamwhole.servicetradecore.model.ShopBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopBrandMapper {
    int countByExample(ShopBrandExample example);

    int deleteByExample(ShopBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopBrand record);

    int insertSelective(ShopBrand record);

    List<ShopBrand> selectByExample(ShopBrandExample example);

    ShopBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopBrand record, @Param("example") ShopBrandExample example);

    int updateByExample(@Param("record") ShopBrand record, @Param("example") ShopBrandExample example);

    int updateByPrimaryKeySelective(ShopBrand record);

    int updateByPrimaryKey(ShopBrand record);
}