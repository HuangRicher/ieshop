package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopUserDO;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.model.SmsLog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface ShopUserService {

    ShopUser queryObject(Integer userId);

    ShopUser queryByOpenId(String openId);

    List<ShopUserDO> queryList(Map<String, Object> map);

    PageInfo<ShopUserDO> queryByPage(Map<String, Object> map,Integer pageNum,Integer pageSize);

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

    boolean subUserWallet(Integer userId, BigDecimal actualPrice);
}
