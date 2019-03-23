package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.HelpIssueMapper;
import com.seamwhole.servicetradecore.model.HelpIssue;
import com.seamwhole.servicetradecore.model.HelpIssueExample;
import com.seamwhole.servicetradecore.service.HelpIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-11-07 11:04:20
 */
@Service
public class HelpIssueServiceImpl implements HelpIssueService {

    @Autowired
    private HelpIssueMapper helpIssueMapper;


    public List<HelpIssue> queryList(Map<String, Object> map) {
        HelpIssueExample example=new HelpIssueExample();
        if(map.get("typeId")!=null)
            example.createCriteria().andTypeIdEqualTo((int)map.get("typeId"));
        return helpIssueMapper.selectByExample(example);
    }
}
