package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("feedback:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<FeedbackEntity> feedbackList = feedbackService.queryList(query);
        int total = feedbackService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(feedbackList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{msgId}")
    @RequiresPermissions("feedback:info")
    public ResponseObject info(@PathVariable("msgId") Integer msgId) {
        FeedbackEntity feedback = feedbackService.queryObject(msgId);

        return ResponseObject.ok().put("feedback", feedback);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("feedback:save")
    public ResponseObject save(@RequestBody FeedbackEntity feedback) {
        feedbackService.save(feedback);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("feedback:update")
    public ResponseObject update(@RequestBody FeedbackEntity feedback) {
        feedbackService.update(feedback);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("feedback:delete")
    public ResponseObject delete(@RequestBody Integer[]msgIds) {
        feedbackService.deleteBatch(msgIds);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<FeedbackEntity> list = feedbackService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
