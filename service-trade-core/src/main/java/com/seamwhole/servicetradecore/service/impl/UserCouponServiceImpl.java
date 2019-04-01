package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.UserCouponMapper;
import com.seamwhole.servicetradecore.mapper.ext.UserCouponExtMapper;
import com.seamwhole.servicetradecore.mapper.model.UserCouponDO;
import com.seamwhole.servicetradecore.model.UserCoupon;
import com.seamwhole.servicetradecore.model.UserCouponExample;
import com.seamwhole.servicetradecore.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class UserCouponServiceImpl implements UserCouponService {

    @Autowired
    private UserCouponMapper userCouponMapper;

    @Autowired
    private UserCouponExtMapper userCouponExtMapper;


    @Override
    public UserCoupon getById(Integer id) {
        return userCouponMapper.selectByPrimaryKey(id);
    }

    public List<UserCoupon> queryList(Map<String, Object> map) {
        return userCouponExtMapper.queryList(map);
    }

    @Override
    public List<UserCouponDO> queryUserCouponList(Map<String, Object> map) {
        return userCouponExtMapper.queryShopUserCouponList(map);
    }

    @Override
    public PageInfo<UserCouponDO> queryUserCouponByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        Page<UserCouponDO> page= PageHelper.startPage(pageNum,pageSize);
        userCouponExtMapper.queryShopUserCouponList(map);
        return page.toPageInfo();
    }

    public void save(UserCoupon goods) {
        userCouponMapper.insertSelective(goods);
    }


    public void update(UserCoupon goods) {
        userCouponMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        UserCouponExample example=new UserCouponExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        userCouponMapper.deleteByExample(example);
    }
}
