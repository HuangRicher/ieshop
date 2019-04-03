package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.FeedBack;

import java.util.List;
import java.util.Map;


public interface FeedBackService {

    FeedBack queryObject(Integer id);

    void save(FeedBack feedbackVo);

    PageInfo<FeedBack> queryByPage(Map<String, Object> params, Integer page, Integer limit);

    FeedBack getById(Integer msgId);

    void updateById(FeedBack feedback);

    void deleteBatch(Integer[] msgIds);

    List<FeedBack> queryList(Map<String, Object> params);
}
