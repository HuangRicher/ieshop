package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopAttributeDO;
import com.seamwhole.servicetradecore.model.ShopAttribute;
import com.seamwhole.servicetradecore.service.AttributeService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopAttribute")
public class ShopAttributeResource {


    @Autowired
    private AttributeService attributeService;


    @PostMapping("/queryByPage")
    public PagesInfo<ShopAttributeDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopAttributeDO> pageInfo=attributeService.queryShopAdByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopAttributeDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public ShopAttribute queryObject(@PathVariable("id") Integer id){
        return attributeService.queryObject(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ShopAttribute attribute){
        attributeService.save(attribute);
    }

    @PostMapping("/update")
    public void update(@RequestBody ShopAttribute attribute){
        attributeService.update(attribute);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        attributeService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<ShopAttributeDO> queryList(@RequestBody Map<String, Object> params){
        return attributeService.queryShopList(params);
    }
}
