package com.seamwhole.weberpadmin.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPathController {

    @RequestMapping("/index")
    public String toIndex(){
        return "index.html";
    }

    @RequestMapping("/register")
    public String toRegister(){
        return "register.html";
    }

    @RequestMapping("/login")
    public String toLogin(){
        return "login.html";
    }

    @RequestMapping("/common/main")
    public String toMain(){
        return "pages/common/main.html";
    }
}
