package com.seamwhole.ssotokenclient.controller;

import com.seamwhole.ssotokenclient.conf.Conf;
import com.seamwhole.ssotokenclient.entity.ReturnT;
import com.seamwhole.ssotokenclient.user.XxlSsoUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public ReturnT<XxlSsoUser> index(HttpServletRequest request) {
        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        return new ReturnT<XxlSsoUser>(xxlUser);
    }

}