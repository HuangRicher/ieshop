package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.UserLevel;
import com.seamwhole.servicetradecore.service.ShopUserLevelService;
import com.seamwhole.util.PagesInfo;
import com.sun.tools.corba.se.idl.StringGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopUserLevel")
public class ShopUserLevelResource {

    @Autowired
    private ShopUserLevelService shopUserLevelService;

    @PostMapping("/queryShopUserLevelList")
    public List<UserLevel> queryShopUserLevelList(@RequestBody Map<String, Object> params){
        return shopUserLevelService.queryList(params);
    }
    @PostMapping("/deleteShopUserLevelBatch")
    public void deleteShopUserLevelBatch(@RequestBody Integer[] ids){
        shopUserLevelService.deleteBatch(ids);
    }
    @PostMapping("/updateShopUserLevelById")
    public void updateShopUserLevelById(@RequestBody UserLevel userLevel){
        shopUserLevelService.updateById(userLevel);
    }
    @PostMapping("/saveShopUserLevel")
    public void saveShopUserLevel(@RequestBody UserLevel userLevel){
        shopUserLevelService.save(userLevel);
    }
    @PostMapping("/getShopUserLevelById/{id}")
    public UserLevel getShopUserLevelById(@PathVariable("id") Integer id){
        return shopUserLevelService.getById(id);
    }
    @PostMapping("/queryShopUserLevel")
    public PagesInfo<UserLevel> queryShopUserLevel(@RequestBody Map<String, Object> params){
        PageInfo<UserLevel> pageInfo=shopUserLevelService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<UserLevel>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }
}
