package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 优惠券Controller
 */
@RestController
@RequestMapping("coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("coupon:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<CouponEntity> couponList = couponService.queryList(query);
        PageUtils pageUtil = new PageUtils(new PageInfo(couponList));

        return ResponseObject.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("coupon:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        CouponEntity coupon = couponService.queryObject(id);

        return ResponseObject.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("coupon:save")
    public ResponseObject save(@RequestBody CouponEntity coupon) {
        couponService.save(coupon);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("coupon:update")
    public ResponseObject update(@RequestBody CouponEntity coupon) {
        couponService.update(coupon);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("coupon:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        couponService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<CouponEntity> list = couponService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }

    /**
     * 按用户、商品下发优惠券
     *
     * @param params
     * @return
     */
    @RequiresPermissions("coupon:publish")
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    public ResponseObject publish(@RequestBody Map<String, Object> params) {
        return couponService.publish(params);
    }
}
