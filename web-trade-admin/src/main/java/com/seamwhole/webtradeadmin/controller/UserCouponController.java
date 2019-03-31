package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.webtradeadmin.service.UserCouponService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("usercoupon")
public class UserCouponController {

    @Autowired
    private UserCouponService userCouponService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("usercoupon:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UserCouponEntity> userCouponList = userCouponService.queryList(query);
        int total = userCouponService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userCouponList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("usercoupon:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        UserCouponEntity userCoupon = userCouponService.queryObject(id);

        return ResponseObject.ok().put("userCoupon", userCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("usercoupon:save")
    public ResponseObject save(@RequestBody UserCouponEntity userCoupon) {
        userCouponService.save(userCoupon);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("usercoupon:update")
    public ResponseObject update(@RequestBody UserCouponEntity userCoupon) {
        userCouponService.update(userCoupon);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("usercoupon:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        userCouponService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<UserCouponEntity> list = userCouponService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
