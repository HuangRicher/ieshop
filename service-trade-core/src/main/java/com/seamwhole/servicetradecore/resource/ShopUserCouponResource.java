package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.UserCouponDO;
import com.seamwhole.servicetradecore.model.UserCoupon;
import com.seamwhole.servicetradecore.service.UserCouponService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopUserCoupon")
public class ShopUserCouponResource {

    @Autowired
    private UserCouponService userCouponService;

    @PostMapping("/queryShopUserCouponByPage")
    public PagesInfo<UserCouponDO> queryShopUserCouponByPage(@RequestBody Map<String, Object> params){
        PageInfo<UserCouponDO> page=userCouponService.queryUserCouponByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<UserCouponDO>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }

    @GetMapping("/queryObject/{id}")
    public UserCoupon queryObject(@PathVariable("id") Integer id){
        return userCouponService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody UserCoupon userCoupon){
        userCouponService.save(userCoupon);
    }

    @PostMapping("/update")
    public void update(@RequestBody UserCoupon userCoupon){
        userCouponService.update(userCoupon);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        userCouponService.deleteBatch(ids);
    }

    @PostMapping("/queryShopUserCouponList")
    public List<UserCouponDO> queryShopUserCouponList(@RequestBody Map<String, Object> params){
        return userCouponService.queryUserCouponList(params);
    }
}
