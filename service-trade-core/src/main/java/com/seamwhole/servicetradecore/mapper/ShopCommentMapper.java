package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.ShopComment;
import com.seamwhole.servicetradecore.model.ShopCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopCommentMapper {
    int countByExample(ShopCommentExample example);

    int deleteByExample(ShopCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopComment record);

    int insertSelective(ShopComment record);

    List<ShopComment> selectByExample(ShopCommentExample example);

    ShopComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopComment record, @Param("example") ShopCommentExample example);

    int updateByExample(@Param("record") ShopComment record, @Param("example") ShopCommentExample example);

    int updateByPrimaryKeySelective(ShopComment record);

    int updateByPrimaryKey(ShopComment record);
}