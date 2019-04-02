package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.HelpIssueDO;

import java.util.List;
import java.util.Map;

public interface HelpIssueExtMapper {

    List<HelpIssueDO> queryShopHelpIssueList(Map<String,Object> map);
}
