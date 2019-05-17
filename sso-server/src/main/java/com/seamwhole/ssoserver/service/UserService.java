package com.seamwhole.ssoserver.service;

import com.seamwhole.ssoserver.entity.ReturnT;
import com.seamwhole.ssoserver.entity.UserInfo;

public interface UserService {

    ReturnT<UserInfo> findUser(String username, String password);

}
