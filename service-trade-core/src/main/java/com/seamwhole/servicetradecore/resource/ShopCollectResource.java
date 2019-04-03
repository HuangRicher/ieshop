package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopCollectDO;
import com.seamwhole.servicetradecore.model.ShopCollect;
import com.seamwhole.servicetradecore.service.CollectService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shopCollect")
public class ShopCollectResource {

    @Autowired
    private CollectService collectService;


    @PostMapping("/queryByPage")
    public PagesInfo<ShopCollectDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopCollectDO> pageInfo=collectService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopCollectDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @PostMapping("/queryObject/{id}")
    public ShopCollect queryObject(@PathVariable("id") Integer id){
        return collectService.queryObject(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ShopCollect collect){
        collectService.save(collect);
    }

    @PostMapping("/update")
    public void update(@RequestBody ShopCollect collect){
        collectService.update(collect);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        collectService.deleteBatch(ids);
    }
}
