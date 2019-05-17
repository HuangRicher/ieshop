package com.seamwhole.ssowebclient.controller;

import com.seamwhole.ssowebclient.conf.Conf;
import com.seamwhole.ssowebclient.entity.ReturnT;
import com.seamwhole.ssowebclient.user.XxlSsoUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {

        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        model.addAttribute("xxlUser", xxlUser);
        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public ReturnT<XxlSsoUser> json(Model model, HttpServletRequest request) {
        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        return new ReturnT(xxlUser);
    }

}