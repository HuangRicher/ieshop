package com.seamwhole.webtradeadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathRedirectController {

    @RequestMapping("/sys/main.html")
    public String loadMain(){
        return "page/sys/main.html";
    }
}
