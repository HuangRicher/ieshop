package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.UserCoupon;
import com.seamwhole.webtradeadmin.info.UserCouponDO;
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
        PagesInfo<UserCouponDO> page=userCouponService.queryShopUserCouponByPage(params);
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("usercoupon:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        UserCoupon userCoupon = userCouponService.queryObject(id);
        return ResponseObject.ok().put("userCoupon", userCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("usercoupon:save")
    public ResponseObject save(@RequestBody UserCoupon userCoupon) {
        userCouponService.save(userCoupon);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("usercoupon:update")
    public ResponseObject update(@RequestBody UserCoupon userCoupon) {
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
        List<UserCouponDO> list = userCouponService.queryShopUserCouponList(params);
        return ResponseObject.ok().put("list", list);
    }
}
