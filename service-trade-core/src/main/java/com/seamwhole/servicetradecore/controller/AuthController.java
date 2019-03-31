package com.seamwhole.servicetradecore.controller;

import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.controller.model.UserModel;
import com.seamwhole.servicetradecore.service.TokenService;
import com.seamwhole.servicetradecore.service.ShopUserService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * API登录授权
 */
@Api(tags = "API登录授权接口")
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Autowired
    private ShopUserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation(value = "登录接口")
    public ResponseObject login(@RequestBody UserModel user) {
        if(StringUtils.isBlank(user.getMobile()))
            throw new CheckException("手机号不能为空");
        if(StringUtils.isBlank(user.getPassword()))
            throw new CheckException("密码不能为空");

        //用户登录
        long userId = userService.login(user.getMobile(), user.getPassword());

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return ResponseObject.ok(map);
    }

    /**
     * 登录
     */
    /*@ApiOperation(value = "登录")
    @PostMapping("login_by_weixin")
    public Object loginByWeixin() {
        JSONObject jsonParam = this.getJsonRequest();
        FullUserInfo fullUserInfo = null;
        String code = "";
        if (!StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        if (null != jsonParam.get("userInfo")) {
            fullUserInfo = jsonParam.getObject("userInfo", FullUserInfo.class);
        }
        if (null == fullUserInfo) {
            return toResponsFail("登录失败");
        }

        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        UserInfo userInfo = fullUserInfo.getUserInfo();

        //获取openid
        String requestUrl = ApiUserUtils.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code
        logger.info("》》》组合token为：" + requestUrl);
        JSONObject sessionData = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return toResponsFail("登录失败");
        }
        //验证用户信息完整性
        String sha1 = CommonUtil.getSha1(fullUserInfo.getRawData() + sessionData.getString("session_key"));
        if (!fullUserInfo.getSignature().equals(sha1)) {
            return toResponsFail("登录失败");
        }
        Date nowTime = new Date();
        ShopUser userVo = userService.queryByOpenId(sessionData.getString("openid"));
        if (null == userVo) {
            userVo = new ShopUser();
            userVo.setUsername("微信用户" + CharUtil.getRandomString(12));
            userVo.setPassword(sessionData.getString("openid"));
            userVo.setRegisterTime(nowTime);
            userVo.setRegisterIp(this.getClientIp());
            userVo.setLastLoginIp(userVo.getRegister_ip());
            userVo.setLastLoginTime(userVo.getRegister_time());
            userVo.setWeixinOpenid(sessionData.getString("openid"));
            userVo.setAvatar(userInfo.getAvatarUrl());
            userVo.setGender(userInfo.getGender()); // //性别 0：未知、1：男、2：女
            userVo.setNickname(userInfo.getNickName());
            userService.save(userVo);
        } else {
            userVo.setLastLoginIp(this.getClientIp());
            userVo.setLastLoginTime(nowTime);
            userService.update(userVo);
        }

        Map<String, Object> tokenMap = tokenService.createToken(userVo.getUserId());
        String token = MapUtils.getString(tokenMap, "token");

        if (null == userInfo || StringUtils.isNullOrEmpty(token)) {
            return toResponsFail("登录失败");
        }

        resultObj.put("token", token);
        resultObj.put("userInfo", userInfo);
        resultObj.put("userId", userVo.getUserId());
        return toResponsSuccess(resultObj);
    }*/
}
