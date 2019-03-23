package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.GoodsIssue;

import java.util.List;
import java.util.Map;


public interface GoodsIssueService {

    List<GoodsIssue> queryList(Map<String, Object> map);

}
