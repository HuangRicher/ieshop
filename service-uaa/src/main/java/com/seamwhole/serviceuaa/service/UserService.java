package com.seamwhole.serviceuaa.service;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
public interface UserService {
    void createUser(String password,String domainId,Integer userId,String nickName,String phoneNumber,String weChat,
                    Integer sex,String email,String header,String qq);
}
