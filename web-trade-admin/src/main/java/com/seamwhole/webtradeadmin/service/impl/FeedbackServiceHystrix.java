package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.FeedBack;
import com.seamwhole.webtradeadmin.service.FeedbackService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FeedbackServiceHystrix implements FeedbackService {
    @Override
    public PagesInfo<FeedBack> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public FeedBack queryObject(Integer msgId) {
        return null;
    }

    @Override
    public void save(FeedBack feedback) {

    }

    @Override
    public void update(FeedBack feedback) {

    }

    @Override
    public void deleteBatch(Integer[] msgIds) {

    }

    @Override
    public List<FeedBack> queryList(Map<String, Object> params) {
        return null;
    }
}
