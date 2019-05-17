package com.seamwhole.ssotokenclient.login;

import com.seamwhole.ssotokenclient.conf.Conf;
import com.seamwhole.ssotokenclient.store.SsoLoginStore;
import com.seamwhole.ssotokenclient.store.SsoSessionIdHelper;
import com.seamwhole.ssotokenclient.user.XxlSsoUser;
import com.seamwhole.ssotokenclient.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SsoWebLoginHelper {

    /**
     * client login
     * @param ifRemember    true: cookie not expire, false: expire when browser close （server cookie）
     */
    public static void login(HttpServletResponse response,
                             String sessionId,
                             XxlSsoUser xxlUser,
                             boolean ifRemember) {
        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            throw new RuntimeException("parseStoreKey Fail, sessionId:" + sessionId);
        }
        SsoLoginStore.put(storeKey, xxlUser);
        CookieUtil.set(response, Conf.SSO_SESSIONID, sessionId, ifRemember);
    }

    /**
     * client logout
     */
    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        String cookieSessionId = CookieUtil.getValue(request, Conf.SSO_SESSIONID);
        if (cookieSessionId==null) {
            return;
        }
        String storeKey = SsoSessionIdHelper.parseStoreKey(cookieSessionId);
        if (storeKey != null) {
            SsoLoginStore.remove(storeKey);
        }
        CookieUtil.remove(request, response, Conf.SSO_SESSIONID);
    }



    /**
     * login check
     */
    public static XxlSsoUser loginCheck(HttpServletRequest request, HttpServletResponse response){
        String cookieSessionId = CookieUtil.getValue(request, Conf.SSO_SESSIONID);
        // cookie user
        XxlSsoUser xxlUser = SsoTokenLoginHelper.loginCheck(cookieSessionId);
        if (xxlUser != null) {
            return xxlUser;
        }
        // redirect user
        // remove old cookie
        SsoWebLoginHelper.removeSessionIdByCookie(request, response);
        // set new cookie
        String paramSessionId = request.getParameter(Conf.SSO_SESSIONID);
        xxlUser = SsoTokenLoginHelper.loginCheck(paramSessionId);
        if (xxlUser != null) {
            CookieUtil.set(response, Conf.SSO_SESSIONID, paramSessionId, false);    // expire when browser close （client cookie）
            return xxlUser;
        }
        return null;
    }


    /**
     * client logout, cookie only
     */
    public static void removeSessionIdByCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.remove(request, response, Conf.SSO_SESSIONID);
    }

    /**
     * get sessionid by cookie
     */
    public static String getSessionIdByCookie(HttpServletRequest request) {
        String cookieSessionId = CookieUtil.getValue(request, Conf.SSO_SESSIONID);
        return cookieSessionId;
    }


}
