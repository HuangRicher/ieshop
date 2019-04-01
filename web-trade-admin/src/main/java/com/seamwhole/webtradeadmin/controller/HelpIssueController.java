package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("helpissue")
public class HelpIssueController {
    @Autowired
    private HelpIssueService helpIssueService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("helpissue:list")
    @ResponseBody
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<HelpIssueEntity> helpIssueList = helpIssueService.queryList(query);
        int total = helpIssueService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(helpIssueList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("helpissue:info")
    @ResponseBody
    public ResponseObject info(@PathVariable("id") Integer id) {
        HelpIssueEntity helpIssue = helpIssueService.queryObject(id);

        return ResponseObject.ok().put("helpIssue", helpIssue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("helpissue:save")
    @ResponseBody
    public ResponseObject save(@RequestBody HelpIssueEntity helpIssue) {
        helpIssueService.save(helpIssue);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("helpissue:update")
    @ResponseBody
    public ResponseObject update(@RequestBody HelpIssueEntity helpIssue) {
        helpIssueService.update(helpIssue);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("helpissue:delete")
    @ResponseBody
    public ResponseObject delete(@RequestBody Integer[] ids) {
        helpIssueService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<HelpIssueEntity> list = helpIssueService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
