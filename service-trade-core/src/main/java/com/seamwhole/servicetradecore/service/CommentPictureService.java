package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.CommentPicture;

import java.util.List;
import java.util.Map;


public interface CommentPictureService {

    List<CommentPicture> queryList(Map<String, Object> map);

    int save(CommentPicture comment);


}
