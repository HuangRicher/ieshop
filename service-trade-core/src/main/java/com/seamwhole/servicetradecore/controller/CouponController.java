package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.annotation.LoginUser;
import com.seamwhole.servicetradecore.constant.RedisKeyConstant;
import com.seamwhole.servicetradecore.controller.model.CouponModel;
import com.seamwhole.servicetradecore.domain.BuyGoods;
import com.seamwhole.servicetradecore.mapper.model.CouponDO;
import com.seamwhole.servicetradecore.mapper.model.ProductDO;
import com.seamwhole.servicetradecore.mapper.model.ShopCartDO;
import com.seamwhole.servicetradecore.model.Coupon;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.model.SmsLog;
import com.seamwhole.servicetradecore.model.UserCoupon;
import com.seamwhole.servicetradecore.redis.RedisService;
import com.seamwhole.servicetradecore.service.*;
import com.seamwhole.servicetradecore.util.CharUtil;
import com.seamwhole.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * API优惠券管理
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "优惠券")
@RestController
@RequestMapping("/api/coupon")
public class CouponController extends BaseController {
    @Autowired
    private ShopUserService userService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private RedisService redisService;



    /**
     * 获取优惠券列表
     */
    @ApiOperation(value = "获取优惠券列表")
    @PostMapping("/list")
    public Object list(@LoginUser ShopUser user) {
        Map param = new HashMap();
        param.put("userId", user.getId());
        List<CouponDO> couponVos = couponService.queryUserCoupons(param);
        return toResponsSuccess(couponVos);
    }

    /**
     * 根据商品获取可用优惠券列表
     */
    @ApiOperation(value = "根据商品获取可用优惠券列表")
    @PostMapping("/listByGoods")
    public Object listByGoods(@LoginUser ShopUser user,@RequestParam(defaultValue = "cart") String type) {
        BigDecimal goodsTotalPrice = new BigDecimal(0.00);
        if (type.equals("cart")) {
            Map param = new HashMap();
            param.put("userId", user.getId());
            List<ShopCartDO> cartList = cartService.queryList(user.getId(), "", null,null,null,"");
            //获取购物车统计信息
            for (ShopCartDO cartItem : cartList) {
                if (null != cartItem.getChecked() && 1 == cartItem.getChecked()) {
                    goodsTotalPrice = goodsTotalPrice.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
                }
            }
        } else { // 是直接购买的
            BuyGoods goodsVo = (BuyGoods) redisService.get(user.getId()+RedisKeyConstant.BUY_GOODS_CACHE);
            ProductDO productInfo = productService.queryObject(goodsVo.getProductId());
            //商品总价
            goodsTotalPrice = productInfo.getRetailPrice().multiply(new BigDecimal(goodsVo.getNumber()));
        }

        // 获取可用优惠券
        Map param = new HashMap();
        param.put("userId", user.getId());
        param.put("couponStatus", 1);
        List<CouponDO> couponVos = couponService.queryUserCoupons(param);
        List<CouponDO> useCoupons = new ArrayList<>();
        List<CouponDO> notUseCoupons = new ArrayList<>();
        for (CouponDO couponVo : couponVos) {
            if (goodsTotalPrice.compareTo(couponVo.getMin_goods_amount()) >= 0) { // 可用优惠券
                couponVo.setEnabled(1);
                useCoupons.add(couponVo);
            } else {
                couponVo.setEnabled(0);
                notUseCoupons.add(couponVo);
            }
        }
        useCoupons.addAll(notUseCoupons);
        return toResponsSuccess(useCoupons);
    }

    /**
     * 兑换优惠券
     */
    @ApiOperation(value = "领券优惠券")
    @PostMapping("exchange")
    public Object exchange(@RequestBody CouponModel couponModel) {
        String couponNumber = couponModel.getCouponNumber();
        if (StringUtils.isNullOrEmpty(couponNumber)) {
            return toResponsFail("当前优惠码无效");
        }
        //
        Map param = new HashMap();
        param.put("couponNumber", couponNumber);
        List<UserCoupon> couponVos = userCouponService.queryList(param);
        UserCoupon userCouponVo = null;
        if (null == couponVos || couponVos.size() == 0) {
            return toResponsFail("当前优惠码无效");
        }
        userCouponVo = couponVos.get(0);
        if (null != userCouponVo.getUserId() && !userCouponVo.getUserId().equals(0L)) {
            return toResponsFail("当前优惠码已经兑换");
        }
        Coupon couponVo = couponService.queryObject(userCouponVo.getCouponId());
        if (null == couponVo || null == couponVo.getUseEndDate() || couponVo.getUseEndDate().before(new Date())) {
            return toResponsFail("当前优惠码已经过期");
        }
        userCouponVo.setUserId(couponModel.getUserId());
        userCouponVo.setAddTime(new Date());
        userCouponService.update(userCouponVo);
        return toResponsSuccess(userCouponVo);
    }

    /**
     * 　　填写手机号码，领券
     */
    @ApiOperation(value = "领券优惠券")
    @PostMapping("newuser")
    public Object newuser(@RequestBody CouponModel couponModel) {
        //
        String phone = couponModel.getPhone();
        String smscode = couponModel.getSmscode();
        // 校验短信码
        SmsLog smsLogVo = userService.querySmsCodeByUserId(couponModel.getUserId());
        if (null != smsLogVo && !smsLogVo.getSmsCode().equals(smscode)) {
            return toResponsFail("短信校验失败");
        }
        // 更新手机号码
        if (!StringUtils.isNullOrEmpty(phone)) {
            if (phone.equals(couponModel.getMobile())) {
                ShopUser user=new ShopUser();
                user.setId(couponModel.getUserId());
                user.setMobile(phone);
                userService.update(user);
            }
        }
        // 判断是否是新用户
        if (!StringUtils.isNullOrEmpty(couponModel.getMobile())) {
            return toResponsFail("当前优惠券只能新用户领取");
        }
        // 是否领取过了
        Map params = new HashMap();
        params.put("userId", couponModel.getUserId());
        params.put("sendType", 4);
        List<CouponDO> couponVos = couponService.queryUserCoupons(params);
        if (null != couponVos && couponVos.size() > 0) {
            return toResponsFail("已经领取过，不能重复领取");
        }
        // 领取
        Map couponParam = new HashMap();
        couponParam.put("sendType", 4);
        CouponDO newCouponConfig = couponService.queryMaxUserEnableCoupon(couponParam);
        if (null != newCouponConfig) {
            UserCoupon userCouponVo = new UserCoupon();
            userCouponVo.setAddTime(new Date());
            userCouponVo.setCouponId(newCouponConfig.getId());
            userCouponVo.setCouponNumber(CharUtil.getRandomString(12));
            userCouponVo.setUserId(couponModel.getUserId());
            userCouponService.save(userCouponVo);
            return toResponsSuccess(userCouponVo);
        } else {
            return toResponsFail("领取失败");
        }
    }

    /**
     * 　　转发领取红包
     */
    @ApiOperation(value = "转发领取红包")
    @PostMapping("transActivit")
    public Object transActivit(@RequestBody CouponModel couponModel) {
        // 是否领取过了
        Map params = new HashMap();
        params.put("userId", couponModel.getUserId());
        params.put("sendType", 2);
        params.put("sourceKey", couponModel.getSourceKey());
        List<CouponDO> couponVos = couponService.queryUserCoupons(params);
        if (null != couponVos && couponVos.size() > 0) {
            return toResponsObject(2, "已经领取过", couponVos);
        }
        // 领取
        Map couponParam = new HashMap();
        couponParam.put("send_type", 2);
        CouponDO newCouponConfig = couponService.queryMaxUserEnableCoupon(couponParam);
        if (null != newCouponConfig) {
            UserCoupon userCouponVo = new UserCoupon();
            userCouponVo.setAddTime(new Date());
            userCouponVo.setCouponId(newCouponConfig.getId());
            userCouponVo.setCouponNumber(CharUtil.getRandomString(12));
            userCouponVo.setUserId(couponModel.getUserId());
            userCouponVo.setSourceKey(couponModel.getSourceKey());
            userCouponVo.setReferrer(couponModel.getReferrer());
            userCouponService.save(userCouponVo);
            //
            List<UserCoupon> userCouponVos = new ArrayList();
            userCouponVos.add(userCouponVo);
            //
            params = new HashMap();
            params.put("userId", couponModel.getUserId());
            params.put("sendType", 2);
            params.put("sourceKey", couponModel.getSourceKey());
            couponVos = couponService.queryUserCoupons(params);
            return toResponsSuccess(couponVos);
        } else {
            return toResponsFail("领取失败");
        }
    }
}
