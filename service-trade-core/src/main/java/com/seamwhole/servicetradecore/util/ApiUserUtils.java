package com.seamwhole.servicetradecore.util;


import org.springframework.beans.factory.annotation.Value;

public class ApiUserUtils {

    @Value("${wx.getCode}")
    private static String wxgetcode;

    private static  String webAccessTokcenhttps="https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";


    private static  String wxAppid="wxddfffbfc4fd5cd2a";


    private static  String wxSecret="b7f1baa9e99cb98bc0f8d1372a807521";

    @Value("${wx.userMessage}")
    private  static String userMessage;

    //替换字符串
    public static String getCode(String APPID, String REDIRECT_URI, String SCOPE) {
        return String.format(wxgetcode, APPID, REDIRECT_URI, SCOPE);
    }

    //替换字符串
    public static String getWebAccess(String CODE) {
        return String.format(webAccessTokcenhttps,wxAppid,wxSecret, CODE);
    }

    //替换字符串
    public static String getUserMessage(String access_token, String openid) {
        return String.format(userMessage, access_token, openid);
    }
}