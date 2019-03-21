package com.seamwhole.serviceuaa.mapper;

import com.seamwhole.serviceuaa.model.UserRolesExample;
import com.seamwhole.serviceuaa.model.UserRolesKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRolesMapper {
    int countByExample(UserRolesExample example);

    int deleteByExample(UserRolesExample example);

    int deleteByPrimaryKey(UserRolesKey key);

    int insert(UserRolesKey record);

    int insertSelective(UserRolesKey record);

    List<UserRolesKey> selectByExample(UserRolesExample example);

    int updateByExampleSelective(@Param("record") UserRolesKey record, @Param("example") UserRolesExample example);

    int updateByExample(@Param("record") UserRolesKey record, @Param("example") UserRolesExample example);
}