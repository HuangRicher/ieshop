package com.seamwhole.serviceuaa.controller;

import com.seamwhole.constant.RedisKeyConstant;
import com.seamwhole.serviceuaa.redis.DistributedLockCallback;
import com.seamwhole.serviceuaa.redis.DistributedLockTemplate;
import com.seamwhole.serviceuaa.redis.RedisService;
import com.seamwhole.serviceuaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@RestController
public class UserController {

    @Resource
    DistributedLockTemplate distributedLockTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;



    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }


    @PostMapping("/register")
    public void register(String password,String domainId, String nickName, String phoneNumber, String weChat,
                         Integer sex, String email, String header, String qq){
        Integer userId=distributedLockTemplate.lock(new DistributedLockCallback<Integer>(){
            @Override
            public Integer process() {
                Object object=redisService.get(RedisKeyConstant.GENERATE_USER_CODE+domainId);
                if(object==null){
                    redisService.set(RedisKeyConstant.GENERATE_USER_CODE+domainId,RedisKeyConstant.INIT_USER_CODE);
                    return RedisKeyConstant.INIT_USER_CODE;
                }else  {
                    Integer userId=(Integer)object;
                    userId+=1;
                    redisService.set(RedisKeyConstant.GENERATE_USER_CODE+domainId,userId);
                    return userId;
                }
            }
            @Override
            public String getLockName() {
                return "lockForUserId"+RedisKeyConstant.GENERATE_USER_CODE+domainId;
            }
        });
         userService.createUser(password,domainId,userId,nickName,phoneNumber,weChat,sex,email,header,qq);
    }
}
