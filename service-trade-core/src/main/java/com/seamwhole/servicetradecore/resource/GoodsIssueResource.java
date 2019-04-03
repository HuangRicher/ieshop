package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.GoodsIssue;
import com.seamwhole.servicetradecore.service.GoodsIssueService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsIssue")
public class GoodsIssueResource {

    @Autowired
    private GoodsIssueService goodsIssueService;

    @PostMapping("/queryByPage")
    public PagesInfo<GoodsIssue> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<GoodsIssue> pageInfo=goodsIssueService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<GoodsIssue>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public GoodsIssue queryObject(@PathVariable("id") Integer id){
        return goodsIssueService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody GoodsIssue goodsIssue){
        goodsIssueService.save(goodsIssue);
    }

    @PostMapping("/update")
    public void update(@RequestBody GoodsIssue goodsIssue){
        goodsIssueService.updateById(goodsIssue);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        goodsIssueService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<GoodsIssue> queryList(@RequestBody Map<String, Object> params){
        return goodsIssueService.queryList(params);
    }
}
