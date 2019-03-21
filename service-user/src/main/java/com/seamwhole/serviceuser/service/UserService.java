package com.seamwhole.serviceuser.service;

import com.seamwhole.serviceuser.model.User;
import com.seamwhole.serviceuser.model.UserExample;

public interface UserService{

    User add(String domainId,Integer userId,String nickName,String phoneNumber,String weChat,
             Integer sex,String email,String header,String qq);

    User getById(String id);

    User getByUserSerial(Integer userId);

    Integer updateByExample(UserExample example , User User);

    User getByUserSerialAndPassword(Integer userSerial,String password);
}
