package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.GoodsIssue;
import com.seamwhole.webtradeadmin.service.GoodsIssueService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsissue")
public class GoodsIssueController {
    @Autowired
    private GoodsIssueService goodsIssueService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsissue:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<GoodsIssue> page=goodsIssueService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsissue:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        GoodsIssue goodsIssue = goodsIssueService.queryObject(id);

        return ResponseObject.ok().put("goodsIssue", goodsIssue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsissue:save")
    public ResponseObject save(@RequestBody GoodsIssue goodsIssue) {
        goodsIssueService.save(goodsIssue);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsissue:update")
    public ResponseObject update(@RequestBody GoodsIssue goodsIssue) {
        goodsIssueService.update(goodsIssue);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goodsissue:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        goodsIssueService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<GoodsIssue> list = goodsIssueService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
