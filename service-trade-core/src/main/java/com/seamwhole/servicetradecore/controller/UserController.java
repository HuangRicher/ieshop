package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.controller.model.UserModel;
import com.seamwhole.servicetradecore.domain.SmsConfigOutInfo;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.model.SmsLog;
import com.seamwhole.servicetradecore.service.SysConfigService;
import com.seamwhole.servicetradecore.service.UserService;
import com.seamwhole.servicetradecore.util.SmsUtil;
import com.seamwhole.util.CharUtil;
import com.seamwhole.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "会员验证")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 发送短信
     */
    @ApiOperation(value = "发送短信")
    @PostMapping("smscode")
    public Object smscode(@RequestBody UserModel userModel) {
        String phone = userModel.getPhone();
        // 一分钟之内不能重复发送短信
        SmsLog smsLogVo = userService.querySmsCodeByUserId(userModel.getUserId());
        if (null != smsLogVo && (System.currentTimeMillis() / 1000 - smsLogVo.getLogDate()) < 1 * 60) {
            return toResponsFail("短信已发送");
        }
        //生成验证码
        String sms_code = CharUtil.getRandomNum(4);
        String msgContent = "您的验证码是：" + sms_code + "，请在页面中提交验证码完成验证。";
        // 发送短信
        String result = "";
        //获取云存储配置信息
        SmsConfigOutInfo config = sysConfigService.getConfigObject(Constant.SMS_CONFIG_KEY, SmsConfigOutInfo.class);
        if (config==null) {
            return toResponsFail("请先配置短信平台信息");
        }
        if (StringUtils.isNullOrEmpty(config.getName())) {
            return toResponsFail("请先配置短信平台用户名");
        }
        if (StringUtils.isNullOrEmpty(config.getPwd())) {
            return toResponsFail("请先配置短信平台密钥");
        }
        if (StringUtils.isNullOrEmpty(config.getSign())) {
            return toResponsFail("请先配置短信平台签名");
        }
        try {
            /**
             * 状态,发送编号,无效号码数,成功提交数,黑名单数和消息，无论发送的号码是多少，一个发送请求只返回一个sendid，如果响应的状态不是“0”，则只有状态和消息
             */
            result = SmsUtil.crSendSms(config.getName(), config.getPwd(), phone, msgContent, config.getSign(), "", "");
        } catch (Exception e) {

        }
        String arr[] = result.split(",");

        if ("0".equals(arr[0])) {
            smsLogVo = new SmsLog();
            smsLogVo.setLogDate(System.currentTimeMillis() / 1000);
            smsLogVo.setUserId(userModel.getUserId());
            smsLogVo.setPhone(phone);
            smsLogVo.setSmsCode(sms_code);
            smsLogVo.setSmsText(msgContent);
            userService.saveSmsCodeLog(smsLogVo);
            return toResponsSuccess("短信发送成功");
        } else {
            return toResponsFail("短信发送失败");
        }
    }

    /**
     * 获取当前会员等级
     */
    @ApiOperation(value = "获取当前会员等级")
    @PostMapping("getUserLevel")
    public Object getUserLevel(@RequestBody UserModel userModel) {
        ShopUser loginUser=new ShopUser();
        loginUser.setUserLevelId(userModel.getUserLevelId());
        String userLevel = userService.getUserLevel(loginUser);
        return toResponsSuccess(userLevel);
    }

    /**
     * 绑定手机
     */
    @ApiOperation(value = "绑定手机")
    @PostMapping("bindMobile")
    public Object bindMobile(@RequestBody UserModel userModel) {
        SmsLog smsLogVo = userService.querySmsCodeByUserId(userModel.getUserId());
        String mobile_code = userModel.getMobileCode();
        String mobile = userModel.getMobile();
        if (!mobile_code.equals(smsLogVo.getSmsCode())) {
            return toResponsFail("验证码错误");
        }
        ShopUser userVo = userService.queryObject(userModel.getUserId());
        userVo.setMobile(mobile);
        userService.update(userVo);
        return toResponsSuccess("手机绑定成功");
    }
}