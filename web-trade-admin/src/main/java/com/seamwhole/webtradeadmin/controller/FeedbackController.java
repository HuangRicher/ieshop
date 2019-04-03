package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.FeedBack;
import com.seamwhole.webtradeadmin.service.FeedbackService;
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
        PagesInfo<FeedBack> page=feedbackService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{msgId}")
    @RequiresPermissions("feedback:info")
    public ResponseObject info(@PathVariable("msgId") Integer msgId) {
        FeedBack feedback = feedbackService.queryObject(msgId);

        return ResponseObject.ok().put("feedback", feedback);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("feedback:save")
    public ResponseObject save(@RequestBody FeedBack feedback) {
        feedbackService.save(feedback);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("feedback:update")
    public ResponseObject update(@RequestBody FeedBack feedback) {
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

        List<FeedBack> list = feedbackService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
