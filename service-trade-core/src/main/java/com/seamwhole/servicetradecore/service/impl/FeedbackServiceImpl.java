package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.FeedBackMapper;
import com.seamwhole.servicetradecore.model.FeedBack;
import com.seamwhole.servicetradecore.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FeedbackServiceImpl implements FeedBackService {
    @Autowired
    private FeedBackMapper feedBackMapper;


    public FeedBack queryObject(Integer id) {
        return feedBackMapper.selectByPrimaryKey(id);
    }

    public void save(FeedBack feedbackVo) {
        feedBackMapper.insertSelective(feedbackVo);
    }

}
