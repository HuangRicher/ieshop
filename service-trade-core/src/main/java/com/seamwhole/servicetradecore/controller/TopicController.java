package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.TopicModel;
import com.seamwhole.servicetradecore.model.ShopTopic;
import com.seamwhole.servicetradecore.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/topic")
public class TopicController extends BaseController {
    @Autowired
    private TopicService topicService;

    /**
     */
    @PostMapping("list")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map param = new HashMap();
        param.put("sidx", "id");
        param.put("order", "desc");
        param.put("fields", "id, title, price_info, scene_pic_url,subtitle");
        //查询列表数据
        PageInfo<ShopTopic> pageInfo=topicService.queryByPage(param,page,size);
        return toResponsSuccess(pageInfo);
    }

    /**
     */
    @PostMapping("detail")
    public Object detail(@RequestBody TopicModel topicModel) {
        ShopTopic topicEntity = topicService.queryObject(topicModel.getId());
        return toResponsSuccess(topicEntity);
    }

    /**
     */
    @PostMapping("related")
    public Object related(@RequestBody TopicModel topicModel) {
        Map param = new HashMap();
        param.put("limit", 4);
        List<ShopTopic> topicEntities = topicService.queryList(param);
        return toResponsSuccess(topicEntities);
    }
}