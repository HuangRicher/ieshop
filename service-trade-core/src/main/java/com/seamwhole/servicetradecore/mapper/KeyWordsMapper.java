package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.KeyWords;
import com.seamwhole.servicetradecore.model.KeyWordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KeyWordsMapper {
    int countByExample(KeyWordsExample example);

    int deleteByExample(KeyWordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KeyWords record);

    int insertSelective(KeyWords record);

    List<KeyWords> selectByExample(KeyWordsExample example);

    KeyWords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KeyWords record, @Param("example") KeyWordsExample example);

    int updateByExample(@Param("record") KeyWords record, @Param("example") KeyWordsExample example);

    int updateByPrimaryKeySelective(KeyWords record);

    int updateByPrimaryKey(KeyWords record);
}