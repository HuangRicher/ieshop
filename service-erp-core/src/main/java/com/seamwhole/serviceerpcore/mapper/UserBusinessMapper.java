package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.UserBusiness;
import com.seamwhole.serviceerpcore.model.UserBusinessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBusinessMapper {
    int countByExample(UserBusinessExample example);

    int deleteByExample(UserBusinessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBusiness record);

    int insertSelective(UserBusiness record);

    List<UserBusiness> selectByExample(UserBusinessExample example);

    UserBusiness selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBusiness record, @Param("example") UserBusinessExample example);

    int updateByExample(@Param("record") UserBusiness record, @Param("example") UserBusinessExample example);

    int updateByPrimaryKeySelective(UserBusiness record);

    int updateByPrimaryKey(UserBusiness record);
}