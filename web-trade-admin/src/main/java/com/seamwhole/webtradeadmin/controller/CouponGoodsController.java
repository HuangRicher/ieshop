package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.webtradeadmin.service.CouponGoodsService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 优惠券关联商品Controller
 */
@RestController
@RequestMapping("coupongoods")
public class CouponGoodsController {
    @Autowired
    private CouponGoodsService couponGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("coupongoods:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CouponGoodsEntity> couponGoodsList = couponGoodsService.queryList(query);
        int total = couponGoodsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(couponGoodsList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("coupongoods:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        CouponGoodsEntity couponGoods = couponGoodsService.queryObject(id);

        return ResponseObject.ok().put("couponGoods", couponGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("coupongoods:save")
    public ResponseObject save(@RequestBody CouponGoodsEntity couponGoods) {
        couponGoodsService.save(couponGoods);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("coupongoods:update")
    public ResponseObject update(@RequestBody CouponGoodsEntity couponGoods) {
        couponGoodsService.update(couponGoods);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("coupongoods:delete")
    public ResponseObject delete(@RequestBody Integer[]ids) {
        couponGoodsService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<CouponGoodsEntity> list = couponGoodsService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
