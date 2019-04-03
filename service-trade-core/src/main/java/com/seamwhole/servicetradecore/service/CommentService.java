package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopCommentDO;
import com.seamwhole.servicetradecore.model.ShopComment;

import java.util.List;
import java.util.Map;


public interface CommentService {

    List<ShopComment> queryList(Map<String, Object> map);

    PageInfo<ShopComment> queryByPage(Map<String, Object> map,Integer pageNum,Integer pageSize,String order);

    int queryTotal(Map<String, Object> map);

    int queryHasPicTotal(Map<String, Object> map);

    int save(ShopComment comment);


    PageInfo<ShopCommentDO> queryShopCommentByPage(Map<String, Object> params, Integer page, Integer limit);

    ShopComment getById(Integer id);

    void updateById(ShopComment comment);

    void deleteBatch(Integer[] ids);

    List<ShopCommentDO> queryShopCommentList(Map<String, Object> params);

    void toggleStatus(ShopComment comment);

    int queryShopTotal(Map<String, Object> params);
}
