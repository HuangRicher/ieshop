package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.CouponMapper;
import com.seamwhole.servicetradecore.mapper.UserCouponMapper;
import com.seamwhole.servicetradecore.mapper.ext.CouponExtMapper;
import com.seamwhole.servicetradecore.mapper.model.CouponDO;
import com.seamwhole.servicetradecore.model.*;
import com.seamwhole.servicetradecore.service.CouponGoodsService;
import com.seamwhole.servicetradecore.service.CouponService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
    @Autowired
    private UserCouponMapper userCouponMapper;
    @Autowired
    private CouponGoodsService couponGoodsService;




    public Coupon queryObject(Integer couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
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

    public void delete(Integer id) {
        couponMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(Integer[] ids) {
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

    @Override
    public void updateUserCoupon(CouponDO couponVo) {
        UserCouponExample example=new UserCouponExample();
        example.createCriteria().andIdEqualTo(couponVo.getUser_coupon_id());
        UserCoupon userCoupon=new UserCoupon();
        userCoupon.setCouponStatus(couponVo.getCoupon_status());
        userCouponMapper.updateByExampleSelective(userCoupon,example);
    }

    @Override
    public ResponseObject publish(Map<String, Object> params) {
        // 发放方式 0：按订单发放 1：按用户发放 2:商品转发送券 3：按商品发放 4:新用户注册  5：线下发放 6评价好评红包（固定或随机红包）
        Integer sendType = MapUtils.getInteger(params, "sendType");
        Integer couponId = MapUtils.getInteger(params, "couponId");
        if (null == sendType) {
            return ResponseObject.error("发放方式不能为空");
        }
        if (null == couponId) {
            return ResponseObject.error("优惠券不能为空");
        }
        if (1 == sendType) {
            String userIds = MapUtils.getString(params, "userIds"); // 下发用户逗号分割
            if (StringUtils.isEmpty(userIds)) {
                return ResponseObject.error("用户不能为空");
            }
            //是否发送短信通知
            boolean sendSms = "true".equals(MapUtils.getString(params, "sendSms"));
            for (String strUserId : userIds.split(",")) {
                if (StringUtils.isEmpty(strUserId)) {
                    continue;
                }
                Integer userId = Integer.valueOf(strUserId);
                UserCoupon userCouponVo = new UserCoupon();
                userCouponVo.setUserId(userId);
                userCouponVo.setCouponId(couponId);
                userCouponVo.setCouponNumber("1");
                userCouponVo.setAddTime(new Date());
                userCouponMapper.insertSelective(userCouponVo);
                if (sendSms) {
                    //UserEntity userEntity = userDao.queryObject(userId);
                    // todo 发送短信

                }
            }
        } else if (3 == sendType) {
            String goodsIds = MapUtils.getString(params, "goodsIds"); // 下发商品逗号分割
            if (StringUtils.isEmpty(goodsIds)) {
                return ResponseObject.error("商品Id不能为空");
            }
            for (String goodsId : goodsIds.split(",")) {
                if (StringUtils.isEmpty(goodsId)) {
                    continue;
                }
                CouponGoods couponGoodsVo = new CouponGoods();
                couponGoodsVo.setCouponId(couponId);
                couponGoodsVo.setGoodsId(Integer.valueOf(goodsId));
                couponGoodsService.save(couponGoodsVo);
            }
        } else {
            return ResponseObject.error("此类优惠券不支持手动发放");
        }
        return ResponseObject.ok("发放成功");
    }

    @Override
    public List<Coupon> queryShopList(Map<String, Object> params) {
        CouponExample example=new CouponExample();
        if(params.get("name")!=null)
            example.createCriteria().andNameLike("%"+params.get("name")+"%");
        return couponMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Coupon> queryShopCommentByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        CouponExample example=new CouponExample();
        if(params.get("name")!=null)
            example.createCriteria().andNameLike("%"+params.get("name")+"%");
        Page<Coupon> page= PageHelper.startPage(pageNum,pageSize);
        couponMapper.selectByExample(example);
        return page.toPageInfo();
    }
}
