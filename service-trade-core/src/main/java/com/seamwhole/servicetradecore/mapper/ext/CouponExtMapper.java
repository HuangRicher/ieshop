package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.CouponDO;

import java.util.List;
import java.util.Map;

public interface CouponExtMapper {

    List<CouponDO> queryList(Map<String,Object> map);

    /**
     * 按条件查询用户优惠券
     *
     * @param params
     * @return
     */
    List<CouponDO> queryUserCoupons(Map<String, Object> params);

    /**
     * 按条件查询用户优惠券
     *
     * @param id
     * @return
     */
    CouponDO getUserCoupon(Integer id);

    /**
     * 按类型查询
     *
     * @param params
     * @return
     */
    CouponDO queryMaxUserEnableCoupon(Map<String, Object> params);

    /**
     * sendType = 1或4 的优惠券
     *
     * @param params
     * @return
     */
    List<CouponDO> queryUserCouponList(Map<String, Object> params);

    int updateUserCoupon(CouponDO couponVo);
}
