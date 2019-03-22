package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopComment;

import java.util.List;
import java.util.Map;


public interface CommentService {

    List<ShopComment> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryHasPicTotal(Map<String, Object> map);

    int save(ShopComment comment);


}
