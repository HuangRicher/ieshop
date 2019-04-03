package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.FeedBack;
import com.seamwhole.servicetradecore.service.FeedBackService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/feedback")
public class FeedbackResource {

    @Autowired
    private FeedBackService feedBackService;


    @PostMapping("/queryByPage")
    public PagesInfo<FeedBack> queryByPage(@RequestBody Map<String, Object> params) {
        PageInfo<FeedBack> pageInfo=feedBackService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<FeedBack>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{msgId}")
    public FeedBack queryObject(@PathVariable("msgId") Integer msgId) {
        return feedBackService.getById(msgId);
    }

    @PostMapping("/save")
    public void save(@RequestBody FeedBack feedback) {
        feedBackService.save(feedback);
    }

    @PostMapping("/update")
    public void update(@RequestBody FeedBack feedback) {
        feedBackService.updateById(feedback);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] msgIds) {
        feedBackService.deleteBatch(msgIds);
    }

    @PostMapping("/queryList")
    public List<FeedBack> queryList(@RequestBody Map<String, Object> params) {
        return feedBackService.queryList(params);
    }
}
