package com.seamwhole.servicetradecore.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.annotation.IgnoreAuth;
import com.seamwhole.servicetradecore.controller.model.UserInfo;
import com.seamwhole.servicetradecore.controller.model.UserModel;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.service.TokenService;
import com.seamwhole.servicetradecore.service.ShopUserService;
import com.seamwhole.servicetradecore.util.ApiUserUtils;
import com.seamwhole.servicetradecore.util.CommonUtil;
import com.seamwhole.servicetradecore.util.ResourceUtil;
import com.seamwhole.servicetradecore.util.ResponseObject;
import com.seamwhole.util.CharUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
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
    @IgnoreAuth
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
     * 微信登录
     */
    @ApiOperation(value = "微信登录")
    @PostMapping("login_by_weixin")
    @IgnoreAuth
    public Object loginByWeixin(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        String code = userInfo.getCode();
        if (null == userInfo) {
            return toResponsFail("登录失败1");
        }

        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        UserInfo.UInfo uInfo = userInfo.getUserInfo();

        //获取openid
        String requestUrl = ApiUserUtils.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code
        logger.info("》》》组合token为：" + requestUrl);
        JSONObject sessionData = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null == sessionData || StringUtils.isBlank(sessionData.getString("openid"))) {
            return toResponsFail("登录失败2");
        }
        //验证用户信息完整性
        String sha1 = CommonUtil.getSha1(userInfo.getRawData() + sessionData.getString("session_key"));
        if (!userInfo.getSignature().equals(sha1)) {
            return toResponsFail("登录失败3");
        }
        Date nowTime = new Date();
        ShopUser userVo = userService.queryByOpenId(sessionData.getString("openid"));
        if (null == userVo) {
            userVo = new ShopUser();
            userVo.setUsername("微信用户" + CharUtil.getRandomString(12));
            userVo.setPassword(sessionData.getString("openid"));
            userVo.setRegisterTime(nowTime);
            userVo.setRegisterIp(this.getClientIp(request));
            userVo.setLastLoginIp(userVo.getRegisterIp());
            userVo.setLastLoginTime(userVo.getRegisterTime());
            userVo.setWeixinOpenid(sessionData.getString("openid"));
            userVo.setAvatar(uInfo.getAvatarUrl());
            userVo.setGender(uInfo.getGender()); // //性别 0：未知、1：男、2：女
            userVo.setNickname(uInfo.getNickName());
            userService.save(userVo);
        } else {
            userVo.setLastLoginIp(this.getClientIp(request));
            userVo.setLastLoginTime(nowTime);
            userService.update(userVo);
        }

        Map<String, Object> tokenMap = tokenService.createToken(userVo.getId());
        String token = MapUtils.getString(tokenMap, "token");

        if (null == userInfo || StringUtils.isBlank(token)) {
            return toResponsFail("登录失败4");
        }

        resultObj.put("token", token);
        resultObj.put("userInfo", uInfo);
        resultObj.put("userId", userVo.getId());
        return toResponsSuccess(resultObj);
    }

    /**
     * 获取请求方IP
     *
     * @return 客户端Ip
     */
    public String getClientIp(HttpServletRequest request) {
        String xff = request.getHeader("X-Real-IP");
        if(xff!=null) {
            return xff;
        }
        xff = request.getHeader("x-forwarded-for");
        if (xff == null) {
            return "8.8.8.8";
        }
        return xff;
    }
    /**
     * 支付宝登录
     */
    /*@ApiOperation(value = "支付宝登录")
    @PostMapping("login_by_ali")
    public Object login_by_ali() {
        JSONObject jsonParam = this.getJsonRequest();
        String code = "";
        if (!com.qiniu.util.StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(ResourceUtil.getConfigByName("ali.webAccessTokenhttps"), ResourceUtil.getConfigByName("ali.appId"), ResourceUtil.getConfigByName("ali.privateKey"),
                "json", "UTF-8", ResourceUtil.getConfigByName("ali.pubKey"), "RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(code);
        request.setGrantType("authorization_code");
        try {
            //code 换取token
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            String accessToken = oauthTokenResponse.getAccessToken();

            //根据token获取用户头像、昵称等信息
            AlipayUserInfoShareRequest userInfoShareRequest = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse userInfoResponse = alipayClient.execute(userInfoShareRequest, accessToken);

            Date nowTime = new Date();
            ShopUser userVo = userService.queryByOpenId(userInfoResponse.getUserId());
            if (null == userVo) {
                userVo = new ShopUser();
                userVo.setUsername("支付宝用户" + CharUtil.getRandomString(12));
                userVo.setPassword(userInfoResponse.getUserId());
                userVo.setRegisterTime(nowTime);
                userVo.setRegisterIp(this.getClientIp());
                userVo.setLastLoginIp(userVo.getRegister_ip());
                userVo.setLastLoginTime(nowTime);
                userVo.setWeixinOpenid(userInfoResponse.getUserId());
                userVo.setAvatar(userInfoResponse.getAvatar());
                //性别 0：未知、1：男、2：女
                //F：女性；M：男性
                userVo.setGender("m".equalsIgnoreCase(userInfoResponse.getGender()) ? 1 : 0);
                userVo.setNickname(userInfoResponse.getNickName());
                userService.save(userVo);
            } else {
                userVo.setLastLoginIp(this.getClientIp());
                userVo.setLastLoginTime(nowTime);
                userService.update(userVo);
            }

            Map<String, Object> tokenMap = tokenService.createToken(userVo.getId());
            String token = MapUtils.getString(tokenMap, "token");

            if (com.qiniu.util.StringUtils.isNullOrEmpty(token)) {
                return toResponsFail("登录失败");
            }

            Map<String, Object> resultObj = new HashMap<String, Object>();
            resultObj.put("token", token);
            resultObj.put("userInfo", userInfoResponse);
            resultObj.put("userId", userVo.getId());
            return toResponsSuccess(resultObj);
        } catch (AlipayApiException e) {
            return toResponsFail("登录失败");
        }
    }*/
}
