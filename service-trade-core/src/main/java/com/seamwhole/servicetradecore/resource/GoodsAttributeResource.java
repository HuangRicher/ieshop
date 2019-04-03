package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.GoodsAttribute;
import com.seamwhole.servicetradecore.service.GoodsAttributeService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/goodsAttribute")
public class GoodsAttributeResource {

    @Autowired
    private GoodsAttributeService goodsAttributeService;


    @PostMapping("/queryByPage")
    public PagesInfo<GoodsAttribute> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<GoodsAttribute> pageInfo=goodsAttributeService.queryShopByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<GoodsAttribute>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public GoodsAttribute queryObject(@PathVariable("id") Integer id){
        return goodsAttributeService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody GoodsAttribute goodsAttribute){
        goodsAttributeService.save(goodsAttribute);
    }

    @PostMapping("/update")
    public void update(@RequestBody GoodsAttribute goodsAttribute){
        goodsAttributeService.updateById(goodsAttribute);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        goodsAttributeService.deleteBatch(ids);
    }
}
