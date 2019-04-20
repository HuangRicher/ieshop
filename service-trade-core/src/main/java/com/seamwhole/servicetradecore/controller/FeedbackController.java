package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.annotation.LoginUser;
import com.seamwhole.servicetradecore.controller.model.FeedBackModel;
import com.seamwhole.servicetradecore.model.FeedBack;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.service.FeedBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@Api(tags = "反馈")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController extends BaseController {
    @Autowired
    private FeedBackService feedBackService;

    /**
     * 添加反馈
     */
    @ApiOperation(value = "添加反馈")
    @PostMapping("save")
    public Object save(@LoginUser ShopUser loginUser, @RequestBody FeedBackModel feedBackModel) {

        if (null != feedBackModel) {
            FeedBack feedbackVo = new FeedBack();
            feedbackVo.setUserId(loginUser.getId());
            feedbackVo.setUserName(loginUser.getUsername());
            feedbackVo.setMobile(feedBackModel.getMobile());
            feedbackVo.setFeedType(feedBackModel.getIndex());
            feedbackVo.setStatus(1);
            feedbackVo.setContent(feedBackModel.getContent());
            feedbackVo.setAddTime(new Date());
            feedBackService.save(feedbackVo);
            return super.toResponsSuccess("感谢你的反馈");
        }
        return super.toResponsFail("反馈失败");
    }
}