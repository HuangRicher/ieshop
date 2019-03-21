package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.CommentPicture;

import java.util.List;
import java.util.Map;


public interface CommentPictureService {

    CommentPicture queryObject(Integer id);

    List<CommentPicture> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int save(CommentPicture comment);

    void update(CommentPicture comment);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
