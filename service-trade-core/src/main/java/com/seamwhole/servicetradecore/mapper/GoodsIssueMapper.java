package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.GoodsIssue;
import com.seamwhole.servicetradecore.model.GoodsIssueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsIssueMapper {
    int countByExample(GoodsIssueExample example);

    int deleteByExample(GoodsIssueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsIssue record);

    int insertSelective(GoodsIssue record);

    List<GoodsIssue> selectByExample(GoodsIssueExample example);

    GoodsIssue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsIssue record, @Param("example") GoodsIssueExample example);

    int updateByExample(@Param("record") GoodsIssue record, @Param("example") GoodsIssueExample example);

    int updateByPrimaryKeySelective(GoodsIssue record);

    int updateByPrimaryKey(GoodsIssue record);
}