package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.HelpIssueDO;
import com.seamwhole.servicetradecore.model.HelpIssue;
import com.seamwhole.servicetradecore.service.HelpIssueService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/helpIssue")
public class HelpIssueResource {

    @Autowired
    private HelpIssueService helpIssueService;


    @PostMapping("/queryByPage")
    public PagesInfo<HelpIssueDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<HelpIssueDO> pageInfo=helpIssueService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<HelpIssueDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public HelpIssue queryObject(@PathVariable("id") Integer id){
        return helpIssueService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody HelpIssue helpIssue){
        helpIssueService.save(helpIssue);
    }

    @PostMapping("/update")
    public void update(@RequestBody HelpIssue helpIssue){
        helpIssueService.updateById(helpIssue);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        helpIssueService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<HelpIssueDO> queryList(@RequestBody Map<String, Object> params){
        return helpIssueService.queryShopHelpIssueList(params);
    }
}
