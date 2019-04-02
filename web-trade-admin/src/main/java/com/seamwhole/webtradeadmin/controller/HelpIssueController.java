package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.HelpIssue;
import com.seamwhole.webtradeadmin.info.HelpIssueDO;
import com.seamwhole.webtradeadmin.service.HelpIssueService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/helpissue")
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
        PagesInfo<HelpIssueDO> page=helpIssueService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("helpissue:info")
    @ResponseBody
    public ResponseObject info(@PathVariable("id") Integer id) {
        HelpIssue helpIssue = helpIssueService.queryObject(id);
        return ResponseObject.ok().put("helpIssue", helpIssue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("helpissue:save")
    @ResponseBody
    public ResponseObject save(@RequestBody HelpIssue helpIssue) {
        helpIssueService.save(helpIssue);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("helpissue:update")
    @ResponseBody
    public ResponseObject update(@RequestBody HelpIssue helpIssue) {
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

        List<HelpIssueDO> list = helpIssueService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
