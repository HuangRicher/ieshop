package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.GoodsIssueMapper;
import com.seamwhole.servicetradecore.model.GoodsIssue;
import com.seamwhole.servicetradecore.model.GoodsIssueExample;
import com.seamwhole.servicetradecore.service.GoodsIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class GoodsIssueServiceImpl implements GoodsIssueService {

    @Autowired
    private GoodsIssueMapper goodsIssueMapper;

    public List<GoodsIssue> queryList(Map<String, Object> map) {
        GoodsIssueExample example=new GoodsIssueExample();
        if(map.get("goodsId")!=null)
            example.createCriteria().andGoodsIdEqualTo((int)map.get("goodsId"));
        return goodsIssueMapper.selectByExample(example);
    }

    @Override
    public PageInfo<GoodsIssue> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        GoodsIssueExample example=new GoodsIssueExample();
        if(params.get("question")!=null)
            example.createCriteria().andQuestionLike("%"+params.get("question")+"%");
        Page<GoodsIssue> page= PageHelper.startPage(pageNum,pageSize);
        goodsIssueMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public GoodsIssue getById(Integer id) {
        return goodsIssueMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(GoodsIssue goodsIssue) {
        goodsIssueMapper.insertSelective(goodsIssue);
    }

    @Override
    public void updateById(GoodsIssue goodsIssue) {
        goodsIssueMapper.updateByPrimaryKeySelective(goodsIssue);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        GoodsIssueExample example=new GoodsIssueExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        goodsIssueMapper.deleteByExample(example);
    }

}
