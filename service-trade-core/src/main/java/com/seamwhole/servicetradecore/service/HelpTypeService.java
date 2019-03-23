package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.HelpType;

import java.util.List;
import java.util.Map;

public interface HelpTypeService {

    List<HelpType> queryList(Map<String, Object> map);
}
