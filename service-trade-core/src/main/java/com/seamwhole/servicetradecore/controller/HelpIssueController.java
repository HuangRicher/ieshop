package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.model.HelpIssue;
import com.seamwhole.servicetradecore.model.HelpType;
import com.seamwhole.servicetradecore.service.HelpIssueService;
import com.seamwhole.servicetradecore.service.HelpTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-11-07 11:04:20
 */
@RestController
@RequestMapping("api/helpissue")
public class HelpIssueController extends BaseController {
    @Autowired
    private HelpIssueService helpIssueService;
    @Autowired
    private HelpTypeService helpTypeService;

    /**
     * 查看帮助类型列表
     */
    @RequestMapping("/typeList")
    public Object typeList() {

        List<HelpType> list = helpTypeService.queryList(new HashMap());

        return toResponsSuccess(list);
    }

    /**
     * 查看问题列表
     */
    @RequestMapping("/issueList")
    public Object issueList(Long type_id) {

        Map params = new HashMap();
        params.put("type_id", type_id);
        List<HelpIssue> helpIssueList = helpIssueService.queryList(params);

        return toResponsSuccess(helpIssueList);
    }
}
