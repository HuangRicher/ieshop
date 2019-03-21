package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.model.ShopUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopUserMapper {
    int countByExample(ShopUserExample example);

    int deleteByExample(ShopUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopUser record);

    int insertSelective(ShopUser record);

    List<ShopUser> selectByExample(ShopUserExample example);

    ShopUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopUser record, @Param("example") ShopUserExample example);

    int updateByExample(@Param("record") ShopUser record, @Param("example") ShopUserExample example);

    int updateByPrimaryKeySelective(ShopUser record);

    int updateByPrimaryKey(ShopUser record);
}