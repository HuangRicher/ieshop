package com.seamwhole.servicetradecore.service.impl;

import com.platform.dao.ApiCouponMapper;
import com.platform.entity.CouponVo;
import com.seamwhole.servicetradecore.mapper.CouponMapper;
import com.seamwhole.servicetradecore.model.Coupon;
import com.seamwhole.servicetradecore.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper couponMapper;

    public Coupon queryObject(Integer couponId) {
        return couponMapper.selectByPrimaryKey(couponId.shortValue());
    }

    public List<Coupon> queryList(Map<String, Object> map) {
        //return apiCouponMapper.queryList(map);
        return null;
    }

    public int queryTotal(Map<String, Object> map) {
        //return apiCouponMapper.queryTotal(map);
        return 0;
    }


    public void save(Coupon userVo) {
        //apiCouponMapper.save(userVo);
    }

    public void update(Coupon user) {
        //apiCouponMapper.updateById(user);
    }

    public void delete(Long userId) {
        //apiCouponMapper.delete(userId);
    }

    public void deleteBatch(Long[] userIds) {
        //apiCouponMapper.deleteBatch(userIds);
    }

    public List<Coupon> queryUserCoupons(Map<String, Object> map) {
        // 检查优惠券是否过期
        List<Coupon> couponVos = apiCouponMapper.queryUserCoupons(map);
        for (Coupon couponVo : couponVos) {
            if (couponVo.getCoupon_status()==1) {
                // 检查是否过期
                if(couponVo.getUse_end_date().before(new Date())) {
                    couponVo.setCoupon_status(3);
                    apiCouponMapper.updateUserCoupon(couponVo);
                }
            }
            if (couponVo.getCoupon_status()==3) {
                // 检查是否不过期
                if(couponVo.getUse_end_date().after(new Date())) {
                    couponVo.setCoupon_status(1);
                    apiCouponMapper.updateUserCoupon(couponVo);
                }
            }
        }

        return couponVos;
    }

    public Coupon queryMaxUserEnableCoupon(Map<String, Object> map) {
        return apiCouponMapper.queryMaxUserEnableCoupon(map);
    }

    public List<Coupon> queryUserCouponList(Map<String, Object> map) {
        return apiCouponMapper.queryUserCouponList(map);
    }
}
