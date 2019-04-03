package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.GoodsIssue;
import com.seamwhole.webtradeadmin.service.GoodsIssueService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GoodsIssueServiceHystrix implements GoodsIssueService {
    @Override
    public PagesInfo<GoodsIssue> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public GoodsIssue queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(GoodsIssue goodsIssue) {

    }

    @Override
    public void update(GoodsIssue goodsIssue) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<GoodsIssue> queryList(Map<String, Object> params) {
        return null;
    }
}
