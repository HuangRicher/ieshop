package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.FeedBack;


public interface FeedBackService {

    FeedBack queryObject(Integer id);

    void save(FeedBack feedbackVo);

}
