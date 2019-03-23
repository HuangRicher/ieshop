package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.HelpIssue;

import java.util.List;
import java.util.Map;

public interface HelpIssueService {

    List<HelpIssue> queryList(Map<String, Object> map);
}
