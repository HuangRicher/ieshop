package com.seamwhole.serviceuaa.service.impl;

import com.seamwhole.serviceuaa.mapper.UserMapper;
import com.seamwhole.serviceuaa.model.User;
import com.seamwhole.serviceuaa.model.UserExample;
import com.seamwhole.serviceuaa.service.UserService;
import com.seamwhole.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(String password,String domainId,Integer userId,String nickName,String phoneNumber,String weChat,
                           Integer sex,String email,String header,String qq) {

        UserExample example=new UserExample();
        example.createCriteria().andUserSerialEqualTo(userId);
        List<User> list=userMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list))
            throw new IllegalArgumentException("user already exists: " + userId);
        User user=new User();
        user.setId(UUIDUtil.generateUUID());
        user.setDomainId(domainId);
        user.setEmail(email);
        user.setHeader(header);
        user.setNickName(nickName);
        user.setPhoneNumber(phoneNumber);
        user.setQq(qq);
        user.setLevel(1);
        user.setSex(sex);
        user.setUserSerial(userId);
        user.setWeChat(weChat);
        String hash = encoder.encode(password.trim());
        user.setPassword(hash);
        userMapper.insert(user);
        log.info("new user has been created: {}", userId);
    }
}
