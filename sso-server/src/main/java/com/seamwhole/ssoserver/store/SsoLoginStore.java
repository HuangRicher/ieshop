package com.seamwhole.ssoserver.store;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.ssoserver.conf.Conf;
import com.seamwhole.ssoserver.user.XxlSsoUser;
import com.seamwhole.ssoserver.util.JedisUtil;

/**
 * local login store
 */
public class SsoLoginStore {

    private static int redisExpireMinite = 1440;    // 1440 minite, 24 hour
    public static void setRedisExpireMinite(int redisExpireMinite) {
        if (redisExpireMinite < 30) {
            redisExpireMinite = 30;
        }
        SsoLoginStore.redisExpireMinite = redisExpireMinite;
    }
    public static int getRedisExpireMinite() {
        return redisExpireMinite;
    }

    /**
     * get
     * @param storeKey
     * @return
     */
    public static XxlSsoUser get(String storeKey) {
        String redisKey = redisKey(storeKey);
        String objectValue = JedisUtil.getStringValue(redisKey);
        if (objectValue != null && objectValue!="") {
            XxlSsoUser xxlUser = JSONObject.parseObject(objectValue,XxlSsoUser.class);
            return xxlUser;
        }
        return null;
    }

    /**
     * remove
     * @param storeKey
     */
    public static void remove(String storeKey) {
        String redisKey = redisKey(storeKey);
        JedisUtil.del(redisKey);
    }

    /**
     * put
     * @param storeKey
     * @param xxlUser
     */
    public static void put(String storeKey, XxlSsoUser xxlUser) {
        String redisKey = redisKey(storeKey);
        JedisUtil.setStringValue(redisKey, JSON.toJSONString(xxlUser), redisExpireMinite * 60);  // minite to second
    }

    private static String redisKey(String sessionId){
        return Conf.SSO_SESSIONID.concat("#").concat(sessionId);
    }

}
