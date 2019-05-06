package com.seamwhole.serviceuserone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/findUserInfo")
    public String findUserInfo(@RequestParam("userId")String userId){
        return "service one : this is a user for user id is"+userId;
    }
}
