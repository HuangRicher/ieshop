package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.UserCouponDO;
import com.seamwhole.servicetradecore.model.UserCoupon;

import java.util.List;
import java.util.Map;


public interface UserCouponService {

    List<UserCoupon> queryList(Map<String, Object> map);

    List<UserCouponDO> queryUserCouponList(Map<String, Object> map);

    PageInfo<UserCouponDO> queryUserCouponByPage(Map<String, Object> map,Integer pageNum,Integer pageSize);

    void save(UserCoupon goods);

    void update(UserCoupon goods);

    UserCoupon getById(Integer id);

    void deleteBatch(Integer[] ids);
}
