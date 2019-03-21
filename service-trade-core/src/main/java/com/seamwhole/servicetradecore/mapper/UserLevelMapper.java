package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.UserLevel;
import com.seamwhole.servicetradecore.model.UserLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLevelMapper {
    int countByExample(UserLevelExample example);

    int deleteByExample(UserLevelExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(UserLevel record);

    int insertSelective(UserLevel record);

    List<UserLevel> selectByExample(UserLevelExample example);

    UserLevel selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") UserLevel record, @Param("example") UserLevelExample example);

    int updateByExample(@Param("record") UserLevel record, @Param("example") UserLevelExample example);

    int updateByPrimaryKeySelective(UserLevel record);

    int updateByPrimaryKey(UserLevel record);
}