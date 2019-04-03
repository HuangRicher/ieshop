package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.FeedBackMapper;
import com.seamwhole.servicetradecore.model.FeedBack;
import com.seamwhole.servicetradecore.model.FeedBackExample;
import com.seamwhole.servicetradecore.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


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

    @Override
    public PageInfo<FeedBack> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        FeedBackExample example=new FeedBackExample();
        Page<FeedBack> page= PageHelper.startPage(pageNum,pageSize);
        feedBackMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public FeedBack getById(Integer msgId) {
        return feedBackMapper.selectByPrimaryKey(msgId);
    }

    @Override
    public void updateById(FeedBack feedback) {
        feedBackMapper.updateByPrimaryKeyWithBLOBs(feedback);
    }

    @Override
    public void deleteBatch(Integer[] msgIds) {
        FeedBackExample example=new FeedBackExample();
        example.createCriteria().andMsgIdIn(Arrays.asList(msgIds));
        feedBackMapper.deleteByExample(example);
    }

    @Override
    public List<FeedBack> queryList(Map<String, Object> params) {
        FeedBackExample example=new FeedBackExample();
        return feedBackMapper.selectByExample(example);
    }
}
