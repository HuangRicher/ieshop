package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.CommentPicture;
import com.seamwhole.servicetradecore.model.CommentPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentPictureMapper {
    int countByExample(CommentPictureExample example);

    int deleteByExample(CommentPictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentPicture record);

    int insertSelective(CommentPicture record);

    List<CommentPicture> selectByExample(CommentPictureExample example);

    CommentPicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentPicture record, @Param("example") CommentPictureExample example);

    int updateByExample(@Param("record") CommentPicture record, @Param("example") CommentPictureExample example);

    int updateByPrimaryKeySelective(CommentPicture record);

    int updateByPrimaryKey(CommentPicture record);
}