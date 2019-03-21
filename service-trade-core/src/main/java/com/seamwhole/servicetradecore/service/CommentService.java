package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopComment;

import java.util.List;
import java.util.Map;


public interface CommentService {

    ShopComment queryObject(Integer id);

    List<ShopComment> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryhasPicTotal(Map<String, Object> map);

    int save(ShopComment comment);

    void update(ShopComment comment);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
