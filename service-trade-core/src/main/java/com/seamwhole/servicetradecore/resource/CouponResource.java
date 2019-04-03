package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.Coupon;
import com.seamwhole.servicetradecore.service.CouponService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coupon")
public class CouponResource {

    @Autowired
    private CouponService couponService;


    @PostMapping("/publish")
    public ResponseObject publish(@RequestBody Map<String, Object> params){
        return couponService.publish(params);
    }

    @PostMapping("/queryList")
    public List<Coupon> queryList(@RequestBody Map<String, Object> params){
        return couponService.queryShopList(params);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        couponService.deleteBatch(ids);
    }

    @PostMapping("/update")
    public void update(@RequestBody Coupon coupon){
        couponService.update(coupon);
    }

    @PostMapping("/save")
    public void save(@RequestBody Coupon coupon){
        couponService.save(coupon);
    }

    @GetMapping("/queryObject/{id}")
    public Coupon queryObject(@PathVariable("id") Integer id){
        return couponService.queryObject(id);
    }

    @PostMapping("/queryByPage")
    public PagesInfo<Coupon> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<Coupon> pageInfo=couponService.queryShopCommentByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<Coupon>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }
}
