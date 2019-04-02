package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.HelpIssueDO;
import com.seamwhole.servicetradecore.model.HelpIssue;

import java.util.List;
import java.util.Map;

public interface HelpIssueService {

    List<HelpIssue> queryList(Map<String, Object> map);

    PageInfo<HelpIssueDO> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize);

    HelpIssue getById(Integer id);

    void save(HelpIssue helpIssue);

    void updateById(HelpIssue helpIssue);

    List<HelpIssueDO> queryShopHelpIssueList(Map<String, Object> params);

    void deleteBatch(Integer[] ids);
}
