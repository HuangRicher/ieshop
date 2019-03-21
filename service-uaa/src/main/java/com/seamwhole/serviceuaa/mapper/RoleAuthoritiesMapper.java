package com.seamwhole.serviceuaa.mapper;

import com.seamwhole.serviceuaa.model.RoleAuthoritiesExample;
import com.seamwhole.serviceuaa.model.RoleAuthoritiesKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleAuthoritiesMapper {
    int countByExample(RoleAuthoritiesExample example);

    int deleteByExample(RoleAuthoritiesExample example);

    int deleteByPrimaryKey(RoleAuthoritiesKey key);

    int insert(RoleAuthoritiesKey record);

    int insertSelective(RoleAuthoritiesKey record);

    List<RoleAuthoritiesKey> selectByExample(RoleAuthoritiesExample example);

    int updateByExampleSelective(@Param("record") RoleAuthoritiesKey record, @Param("example") RoleAuthoritiesExample example);

    int updateByExample(@Param("record") RoleAuthoritiesKey record, @Param("example") RoleAuthoritiesExample example);
}