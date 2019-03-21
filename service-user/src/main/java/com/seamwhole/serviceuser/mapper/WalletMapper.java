package com.seamwhole.serviceuser.mapper;

import com.seamwhole.serviceuser.model.Wallet;
import com.seamwhole.serviceuser.model.WalletExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalletMapper {
    int countByExample(WalletExample example);

    int deleteByExample(WalletExample example);

    int deleteByPrimaryKey(String userId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    List<Wallet> selectByExample(WalletExample example);

    Wallet selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") Wallet record, @Param("example") WalletExample example);

    int updateByExample(@Param("record") Wallet record, @Param("example") WalletExample example);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);
}