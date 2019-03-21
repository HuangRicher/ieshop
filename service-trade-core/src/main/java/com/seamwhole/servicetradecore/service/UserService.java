package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.model.SmsLog;

import java.util.List;
import java.util.Map;


public interface UserService {

    ShopUser queryObject(Integer userId);

    ShopUser queryByOpenId(String openId);

    List<ShopUser> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(String mobile, String password);

    void save(ShopUser userVo);

    void update(ShopUser user);

    void delete(Integer userId);

    void deleteBatch(Integer[] userIds);

    ShopUser queryByMobile(String mobile);

    long login(String mobile, String password);

    SmsLog querySmsCodeByUserId(Integer userId);

    int saveSmsCodeLog(SmsLog smsLogVo);

    String getUserLevel(ShopUser loginUser);
}
