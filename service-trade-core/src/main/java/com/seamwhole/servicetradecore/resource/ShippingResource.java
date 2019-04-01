package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ShopShipping;
import com.seamwhole.servicetradecore.service.ShippingService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shipping")
public class ShippingResource {

    @Autowired
    private ShippingService shippingService;

    @PostMapping("/queryByPage")
    public PagesInfo<ShopShipping> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopShipping> pageInfo=shippingService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopShipping>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public ShopShipping queryObject(@PathVariable("id") Integer id){
       return shippingService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ShopShipping shipping){
        shippingService.save(shipping);
    }

    @PostMapping("/update")
    public void update(@RequestBody ShopShipping shipping){
        shippingService.updateById(shipping);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        shippingService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<ShopShipping> queryList(@RequestBody Map<String, Object> params){
        return shippingService.queryList(params);
    }
}
