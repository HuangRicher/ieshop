package com.seamwhole.webtradeadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SysPathRedirectController {

    @RequestMapping("/main")
    public String loadMain(){
        return "page/sys/main";
    }

    @RequestMapping("/user")
    public String loadUser(){
        return "page/sys/user";
    }

    @RequestMapping("/smslog")
    public String loadSMSLog(){
        return "page/sys/smslog";
    }

    @RequestMapping("/schedule")
    public String loadSchedule(){
        return "page/sys/schedule";
    }

    @RequestMapping("/role")
    public String loadRole(){
        return "page/sys/role";
    }

    @RequestMapping("/region")
    public String loadRegion(){
        return "page/sys/region";
    }

    @RequestMapping("/oss")
    public String loadOss(){
        return "page/sys/oss";
    }

    @RequestMapping("/menu")
    public String loadMenu(){
        return "page/sys/menu";
    }

    @RequestMapping("/macro")
    public String loadMacro(){
        return "page/sys/macro";
    }

    @RequestMapping("/log")
    public String loadLog(){
        return "page/sys/log";
    }

    @RequestMapping("/generator")
    public String loadGenerator(){
        return "page/sys/generator";
    }

    @RequestMapping("/dept")
    public String loadDept(){
        return "page/sys/dept";
    }

    @RequestMapping("/config")
    public String loadConfig(){
        return "page/sys/config";
    }

}
