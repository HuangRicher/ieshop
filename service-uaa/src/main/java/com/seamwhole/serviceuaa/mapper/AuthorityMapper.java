package com.seamwhole.serviceuaa.mapper;

import com.seamwhole.serviceuaa.model.Authority;
import com.seamwhole.serviceuaa.model.AuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorityMapper {
    int countByExample(AuthorityExample example);

    int deleteByExample(AuthorityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Authority record);

    int insertSelective(Authority record);

    List<Authority> selectByExample(AuthorityExample example);

    Authority selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Authority record, @Param("example") AuthorityExample example);

    int updateByExample(@Param("record") Authority record, @Param("example") AuthorityExample example);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
}