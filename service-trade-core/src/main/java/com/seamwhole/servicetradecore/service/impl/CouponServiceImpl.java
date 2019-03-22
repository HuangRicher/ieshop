package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.CouponMapper;
import com.seamwhole.servicetradecore.mapper.ext.CouponExtMapper;
import com.seamwhole.servicetradecore.mapper.model.CouponDO;
import com.seamwhole.servicetradecore.model.Coupon;
import com.seamwhole.servicetradecore.model.CouponExample;
import com.seamwhole.servicetradecore.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private CouponExtMapper couponExtMapper;




    public Coupon queryObject(Integer couponId) {
        return couponMapper.selectByPrimaryKey(couponId.shortValue());
    }

    public List<CouponDO> queryList(Map<String, Object> map) {
        return couponExtMapper.queryList(map);

    }

    public int queryTotal(Map<String, Object> map) {
        //return apiCouponMapper.queryTotal(map);
        return 0;
    }


    public void save(Coupon coupon) {
        couponMapper.insertSelective(coupon);
    }

    public void update(Coupon coupon) {
        couponMapper.updateByPrimaryKeySelective(coupon);
    }

    public void delete(Short id) {
        couponMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(Short[] ids) {
        CouponExample example=new CouponExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        couponMapper.deleteByExample(example);
    }

    public List<CouponDO> queryUserCoupons(Map<String, Object> map) {
        // 检查优惠券是否过期
        List<CouponDO> couponVos = couponExtMapper.queryUserCoupons(map);
        for (CouponDO couponVo : couponVos) {
            if (couponVo.getCoupon_status()==1) {
                // 检查是否过期
                if(couponVo.getUse_end_date().before(new Date())) {
                    couponVo.setCoupon_status(3);
                    couponExtMapper.updateUserCoupon(couponVo);
                }
            }
            if (couponVo.getCoupon_status()==3) {
                // 检查是否不过期
                if(couponVo.getUse_end_date().after(new Date())) {
                    couponVo.setCoupon_status(1);
                    couponExtMapper.updateUserCoupon(couponVo);
                }
            }
        }

        return couponVos;
    }

    public CouponDO queryMaxUserEnableCoupon(Map<String, Object> map) {
        return couponExtMapper.queryMaxUserEnableCoupon(map);
    }

    public List<CouponDO> queryUserCouponList(Map<String, Object> map) {
        return couponExtMapper.queryUserCouponList(map);
    }

    @Override
    public CouponDO getUserCoupon(Integer id) {
        return couponExtMapper.getUserCoupon(id);
    }
}
