package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopCartDO;
import com.seamwhole.servicetradecore.model.ShopCart;
import com.seamwhole.servicetradecore.service.CartService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shopCart")
public class ShopCartResource {

    @Autowired
    private CartService cartService;


    @PostMapping("/queryByPage")
    public PagesInfo<ShopCartDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopCartDO> pageInfo=cartService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopCartDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }
    @GetMapping("/queryObject/{id}")
    public ShopCart queryObject(@PathVariable("id") Integer id){
        return cartService.queryObject(id);
    }
    @PostMapping("/save")
    public void save(@RequestBody ShopCart cart){
        cartService.save(cart);
    }
    @PostMapping("/update")
    public void update(@RequestBody ShopCart cart){
        cartService.update(cart);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        cartService.deleteBatch(ids);
    }
}
