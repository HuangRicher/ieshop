package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.FeedBack;

import java.util.List;
import java.util.Map;


public interface FeedBackService {

    FeedBack queryObject(Integer id);

    List<FeedBack> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(FeedBack feedbackVo);

    void update(FeedBack feedbackVo);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
