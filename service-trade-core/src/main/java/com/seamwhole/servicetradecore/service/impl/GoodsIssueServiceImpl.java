package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.GoodsIssueMapper;
import com.seamwhole.servicetradecore.model.GoodsIssue;
import com.seamwhole.servicetradecore.model.GoodsIssueExample;
import com.seamwhole.servicetradecore.service.GoodsIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GoodsIssueServiceImpl implements GoodsIssueService {

    @Autowired
    private GoodsIssueMapper GoodsIssueMapper;

    public List<GoodsIssue> queryList(Map<String, Object> map) {
        GoodsIssueExample example=new GoodsIssueExample();
        if(map.get("goodsId")!=null)
            example.createCriteria().andGoodsIdEqualTo((int)map.get("goodsId"));
        return GoodsIssueMapper.selectByExample(example);
    }

}
