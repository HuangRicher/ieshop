package com.seamwhole.serviceuser.controller;

import com.seamwhole.constant.RedisKeyConstant;
import com.seamwhole.serviceuser.model.User;
import com.seamwhole.serviceuser.redis.DistributedLockCallback;
import com.seamwhole.serviceuser.redis.DistributedLockTemplate;
import com.seamwhole.serviceuser.redis.RedisService;
import com.seamwhole.serviceuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    DistributedLockTemplate distributedLockTemplate;

    @Autowired
    private RedisService redisService;

    @PostMapping("/register")
    public User register(String domainId, String nickName, String phoneNumber, String weChat,
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
        return userService.add(domainId,userId,nickName,phoneNumber,weChat,sex,email,header,qq);
    }

    @PostMapping("/checkUser")
    public User checkUser(Integer userId,String password){
        User check=userService.getByUserSerialAndPassword(userId,password);
        if(check!=null){
            User user=new User();
            user.setId(check.getId());
            user.setUserSerial(check.getUserSerial());
            user.setHeader(check.getHeader());
            user.setDomainId(check.getDomainId());
            user.setNickName(check.getNickName());
            user.setSex(check.getSex());
            return user;
        }else{
            return null;
        }
    }

}
