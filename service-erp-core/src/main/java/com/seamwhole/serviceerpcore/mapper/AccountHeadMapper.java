package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.AccountHead;
import com.seamwhole.serviceerpcore.model.AccountHeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountHeadMapper {
    int countByExample(AccountHeadExample example);

    int deleteByExample(AccountHeadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountHead record);

    int insertSelective(AccountHead record);

    List<AccountHead> selectByExample(AccountHeadExample example);

    AccountHead selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountHead record, @Param("example") AccountHeadExample example);

    int updateByExample(@Param("record") AccountHead record, @Param("example") AccountHeadExample example);

    int updateByPrimaryKeySelective(AccountHead record);

    int updateByPrimaryKey(AccountHead record);
}