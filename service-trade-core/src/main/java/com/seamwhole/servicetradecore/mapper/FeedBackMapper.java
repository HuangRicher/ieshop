package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.FeedBack;
import com.seamwhole.servicetradecore.model.FeedBackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedBackMapper {
    int countByExample(FeedBackExample example);

    int deleteByExample(FeedBackExample example);

    int deleteByPrimaryKey(Integer msgId);

    int insert(FeedBack record);

    int insertSelective(FeedBack record);

    List<FeedBack> selectByExampleWithBLOBs(FeedBackExample example);

    List<FeedBack> selectByExample(FeedBackExample example);

    FeedBack selectByPrimaryKey(Integer msgId);

    int updateByExampleSelective(@Param("record") FeedBack record, @Param("example") FeedBackExample example);

    int updateByExampleWithBLOBs(@Param("record") FeedBack record, @Param("example") FeedBackExample example);

    int updateByExample(@Param("record") FeedBack record, @Param("example") FeedBackExample example);

    int updateByPrimaryKeySelective(FeedBack record);

    int updateByPrimaryKeyWithBLOBs(FeedBack record);

    int updateByPrimaryKey(FeedBack record);
}