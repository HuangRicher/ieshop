package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.TopicCategory;
import com.seamwhole.servicetradecore.model.TopicCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicCategoryMapper {
    int countByExample(TopicCategoryExample example);

    int deleteByExample(TopicCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TopicCategory record);

    int insertSelective(TopicCategory record);

    List<TopicCategory> selectByExample(TopicCategoryExample example);

    TopicCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TopicCategory record, @Param("example") TopicCategoryExample example);

    int updateByExample(@Param("record") TopicCategory record, @Param("example") TopicCategoryExample example);

    int updateByPrimaryKeySelective(TopicCategory record);

    int updateByPrimaryKey(TopicCategory record);
}