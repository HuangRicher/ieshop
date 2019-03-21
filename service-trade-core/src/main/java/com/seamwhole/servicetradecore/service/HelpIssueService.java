package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.HelpIssue;

import java.util.List;
import java.util.Map;

public interface HelpIssueService {

    HelpIssue queryObject(Integer id);

    List<HelpIssue> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int save(HelpIssue helpIssue);

    int update(HelpIssue helpIssue);

    int delete(Integer id);

    int deleteBatch(Integer[] ids);
}
