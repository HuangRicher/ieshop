package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopCollect;
import com.seamwhole.servicetradecore.model.ShopCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopCollectMapper {
    int countByExample(ShopCollectExample example);

    int deleteByExample(ShopCollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopCollect record);

    int insertSelective(ShopCollect record);

    List<ShopCollect> selectByExample(ShopCollectExample example);

    ShopCollect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopCollect record, @Param("example") ShopCollectExample example);

    int updateByExample(@Param("record") ShopCollect record, @Param("example") ShopCollectExample example);

    int updateByPrimaryKeySelective(ShopCollect record);

    int updateByPrimaryKey(ShopCollect record);
}