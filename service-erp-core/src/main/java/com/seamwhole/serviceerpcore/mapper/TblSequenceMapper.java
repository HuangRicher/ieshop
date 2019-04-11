package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.TblSequence;
import com.seamwhole.serviceerpcore.model.TblSequenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblSequenceMapper {
    int countByExample(TblSequenceExample example);

    int deleteByExample(TblSequenceExample example);

    int deleteByPrimaryKey(String seqName);

    int insert(TblSequence record);

    int insertSelective(TblSequence record);

    List<TblSequence> selectByExample(TblSequenceExample example);

    TblSequence selectByPrimaryKey(String seqName);

    int updateByExampleSelective(@Param("record") TblSequence record, @Param("example") TblSequenceExample example);

    int updateByExample(@Param("record") TblSequence record, @Param("example") TblSequenceExample example);

    int updateByPrimaryKeySelective(TblSequence record);

    int updateByPrimaryKey(TblSequence record);
}