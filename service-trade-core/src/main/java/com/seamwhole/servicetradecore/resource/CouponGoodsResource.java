package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.CouponGoods;
import com.seamwhole.servicetradecore.service.CouponGoodsService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/couponGoods")
public class CouponGoodsResource {

    @Autowired
    private CouponGoodsService couponGoodsService;

    @PostMapping("/queryByPage")
    public PagesInfo<CouponGoods> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<CouponGoods> pageInfo=couponGoodsService.queryShopCommentByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<CouponGoods>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public CouponGoods queryObject(@PathVariable("id") Integer id){
        return couponGoodsService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody CouponGoods couponGoods){
        couponGoodsService.save(couponGoods);
    }

    @PostMapping("/update")
    public void update(@RequestBody CouponGoods couponGoods){
        couponGoodsService.updateById(couponGoods);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        couponGoodsService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<CouponGoods> queryList(@RequestBody Map<String, Object> params){
        return couponGoodsService.queryList(params);
    }
}
