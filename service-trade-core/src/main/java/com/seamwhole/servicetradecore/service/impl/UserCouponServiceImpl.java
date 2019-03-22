package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.UserCouponMapper;
import com.seamwhole.servicetradecore.mapper.ext.UserCouponExtMapper;
import com.seamwhole.servicetradecore.model.UserCoupon;
import com.seamwhole.servicetradecore.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserCouponServiceImpl implements UserCouponService {

    @Autowired
    private UserCouponMapper userCouponMapper;

    @Autowired
    private UserCouponExtMapper userCouponExtMapper;



    public List<UserCoupon> queryList(Map<String, Object> map) {
        return userCouponExtMapper.queryList(map);
    }



    public void save(UserCoupon goods) {
        userCouponMapper.insertSelective(goods);
    }


    public void update(UserCoupon goods) {
        userCouponMapper.updateByPrimaryKeySelective(goods);
    }


}
