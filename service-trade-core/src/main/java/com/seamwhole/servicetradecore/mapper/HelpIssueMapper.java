package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.HelpIssue;
import com.seamwhole.servicetradecore.model.HelpIssueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HelpIssueMapper {
    int countByExample(HelpIssueExample example);

    int deleteByExample(HelpIssueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HelpIssue record);

    int insertSelective(HelpIssue record);

    List<HelpIssue> selectByExample(HelpIssueExample example);

    HelpIssue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HelpIssue record, @Param("example") HelpIssueExample example);

    int updateByExample(@Param("record") HelpIssue record, @Param("example") HelpIssueExample example);

    int updateByPrimaryKeySelective(HelpIssue record);

    int updateByPrimaryKey(HelpIssue record);
}