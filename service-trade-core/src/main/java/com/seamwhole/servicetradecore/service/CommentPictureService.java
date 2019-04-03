package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.CommentPicture;

import java.util.List;
import java.util.Map;


public interface CommentPictureService {

    List<CommentPicture> queryList(Map<String, Object> map);

    int save(CommentPicture comment);


    PageInfo<CommentPicture> queryByPage(Map<String, Object> params, Integer page, Integer limit);

    CommentPicture getById(Integer id);

    void updateById(CommentPicture commentPicture);

    void deleteBatch(Integer[] ids);
}
