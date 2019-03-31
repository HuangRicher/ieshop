package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.mapper.ShopUserMapper;
import com.seamwhole.servicetradecore.mapper.SmsLogMapper;
import com.seamwhole.servicetradecore.mapper.UserLevelMapper;
import com.seamwhole.servicetradecore.mapper.ext.ShopUserExtMapper;
import com.seamwhole.servicetradecore.mapper.ext.SmsLogExtMapper;
import com.seamwhole.servicetradecore.mapper.model.ShopUserDO;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.model.ShopUserExample;
import com.seamwhole.servicetradecore.model.SmsLog;
import com.seamwhole.servicetradecore.model.UserLevel;
import com.seamwhole.servicetradecore.service.ShopUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ShopUserServiceImpl implements ShopUserService {

    @Autowired
    private ShopUserMapper shopUserMapper;

    @Autowired
    private ShopUserExtMapper shopUserExtMapper;

    @Autowired
    private UserLevelMapper userLevelMapper;

    @Autowired
    private SmsLogMapper smsLogMapper;

    @Autowired
    private SmsLogExtMapper smsLogExtMapper;




    public ShopUser queryObject(Integer userId) {
        return shopUserMapper.selectByPrimaryKey(userId);
    }

    public ShopUser queryByOpenId(String openId) {
        ShopUserExample example=new ShopUserExample();
        example.createCriteria().andWeixinOpenidEqualTo(openId);
        List<ShopUser> list=shopUserMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public PageInfo<ShopUserDO> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        Page<ShopUserDO> page= PageHelper.startPage(pageNum,pageSize);
        shopUserExtMapper.queryList(map);
        return page.toPageInfo();
    }

    public List<ShopUserDO> queryList(Map<String, Object> map) {
        return shopUserExtMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        ShopUserExample example=new ShopUserExample();
        if(map.get("username")!=null)
            example.createCriteria().andUsernameLike("%"+map.get("username")+"%");
        return shopUserMapper.countByExample(example);
    }

    public void save(String mobile, String password) {
        ShopUser user = new ShopUser();
        user.setMobile(mobile);
        user.setUsername(mobile);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setRegisterTime(new Date());
        shopUserMapper.insertSelective(user);
    }

    public void save(ShopUser userVo) {
        userVo.setRegisterTime(new Date());
        shopUserMapper.insertSelective(userVo);
    }

    public void update(ShopUser user) {
        shopUserMapper.updateByPrimaryKeySelective(user);
    }

    public void delete(Integer userId) {
        shopUserMapper.deleteByPrimaryKey(userId);
    }

    public void deleteBatch(Integer[] userIds) {
        ShopUserExample example=new ShopUserExample();
        example.createCriteria().andIdIn(Arrays.asList(userIds));
        shopUserMapper.deleteByExample(example);
    }

    public ShopUser queryByMobile(String mobile) {
        ShopUserExample example=new ShopUserExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<ShopUser> list=shopUserMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }

    public long login(String mobile, String password) {
        ShopUser user = queryByMobile(mobile);
        if(user==null)
            throw new CheckException("手机号或密码错误");

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new CheckException("手机号或密码错误");
        }
        return user.getId();
    }

    public SmsLog querySmsCodeByUserId(Integer userId) {
        return smsLogExtMapper.querySmsCodeByUserId(userId);
    }


    public int saveSmsCodeLog(SmsLog smsLogVo) {
        return smsLogMapper.insertSelective(smsLogVo);
    }

    public String getUserLevel(ShopUser loginUser) {
        String result = "普通用户";
        UserLevel userLevelVo = userLevelMapper.selectByPrimaryKey(loginUser.getUserLevelId());
        if (null != userLevelVo) {
            result = userLevelVo.getName();
        }
        return result;
    }
}
