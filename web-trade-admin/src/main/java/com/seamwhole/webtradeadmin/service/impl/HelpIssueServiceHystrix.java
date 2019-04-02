package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.HelpIssue;
import com.seamwhole.webtradeadmin.info.HelpIssueDO;
import com.seamwhole.webtradeadmin.service.HelpIssueService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HelpIssueServiceHystrix implements HelpIssueService {
    @Override
    public PagesInfo<HelpIssueDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public HelpIssue queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(HelpIssue helpIssue) {

    }

    @Override
    public void update(HelpIssue helpIssue) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<HelpIssueDO> queryList(Map<String, Object> params) {
        return null;
    }
}
