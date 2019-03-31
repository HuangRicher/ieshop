package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.UserLevel;

import java.util.List;
import java.util.Map;

public interface ShopUserLevelService {

    List<UserLevel> queryList(Map<String, Object> params);

    void deleteBatch(Integer[] ids);

    void updateById(UserLevel userLevel);

    void save(UserLevel userLevel);

    UserLevel getById(Integer id);

    PageInfo<UserLevel> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);
}
