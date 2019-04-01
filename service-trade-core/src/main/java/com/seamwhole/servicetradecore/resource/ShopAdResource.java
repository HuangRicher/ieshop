package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopAdDO;
import com.seamwhole.servicetradecore.model.ShopAd;
import com.seamwhole.servicetradecore.service.AdService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopAd")
public class ShopAdResource {

    @Autowired
    private AdService adService;


    @PostMapping("/queryByPage")
    public PagesInfo<ShopAdDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopAdDO> pageInfo=adService.queryShopAdByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopAdDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public ShopAd queryObject(@PathVariable("id") Integer id){
        return adService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ShopAd ad){
        adService.save(ad);
    }

    @PostMapping("/update")
    public void update(@RequestBody ShopAd ad){
        adService.updateById(ad);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        adService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<ShopAdDO> queryList(@RequestBody Map<String, Object> params){
        return adService.queryShopAdList(params);
    }
}
