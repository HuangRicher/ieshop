package com.seamwhole.serviceuser.service.impl;

import com.seamwhole.serviceuser.mapper.UserMapper;
import com.seamwhole.serviceuser.model.User;
import com.seamwhole.serviceuser.model.UserExample;
import com.seamwhole.serviceuser.service.UserService;
import com.seamwhole.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;




    @Override
    public User add(String domainId, Integer userId, String nickName, String phoneNumber, String weChat,
                    Integer sex, String email, String header, String qq) {
        User user=new User();
        user.setId(UUIDUtil.generateUUID());
        user.setDomainId(domainId);
        user.setEmail(email);
        user.setHeader(header);
        user.setNickName(nickName);
        user.setPhoneNumber(phoneNumber);
        user.setQq(qq);
        user.setSex(sex);
        user.setUserSerial(userId);
        user.setWeChat(weChat);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User getByUserSerial(Integer userId) {
        UserExample example=new UserExample();
        example.createCriteria().andUserSerialEqualTo(userId);
        List<User> list=userMapper.selectByExample(example);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public User getByUserSerialAndPassword(Integer userSerial, String password) {
        UserExample example=new UserExample();
        example.createCriteria().andUserSerialEqualTo(userSerial).andPasswordEqualTo(password);
        List<User> list=userMapper.selectByExample(example);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer updateByExample(UserExample example, User user) {
        return userMapper.updateByExample(user,example);
    }

    @Override
    public User getById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
