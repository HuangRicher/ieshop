package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopUserDO;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.service.ShopUserService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopUser")
public class ShopUserResource {

    @Autowired
    private ShopUserService shopUserService;

    @PostMapping("/queryShopUseByPage")
    public PagesInfo<ShopUserDO> queryShopUseByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopUserDO> pageInfo= shopUserService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopUserDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/getShopUserById/{userId}")
    public ShopUser getShopUserById(@PathVariable("userId") Integer userId){
        return shopUserService.queryObject(userId);
    }

    @PostMapping("/saveShopUser")
    public void saveShopUser(@RequestBody ShopUser user){
        shopUserService.save(user);
    }
    @PostMapping("/updateShopUserById")
    public void updateShopUserById(@RequestBody ShopUser user){
        shopUserService.update(user);
    }
    @PostMapping("/deleteShopUserBatch")
    public void deleteShopUserBatch(@RequestBody Integer[] ids){
        shopUserService.deleteBatch(ids);
    }
    @PostMapping("/queryShopUserList")
    public List<ShopUserDO> queryShopUserList(@RequestBody Map<String, Object> params){
        return shopUserService.queryList(params);
    }
    @PostMapping("/queryShopUserTotal")
    public int queryShopUserTotal(@RequestBody Map<String, Object> params){
        return shopUserService.queryTotal(params);
    }
}
