package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.UserLevel;
import com.seamwhole.webtradeadmin.service.ShopUserLevelService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-16 16:52:22
 */
@RestController
@RequestMapping("userlevel")
public class ShopUserLevelController {

    @Autowired
    private ShopUserLevelService shopUserLevelService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("userlevel:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<UserLevel> page=shopUserLevelService.queryShopUserLevel(params);

        return ResponseObject.ok().put("page", page);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("userlevel:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        UserLevel userLevel = shopUserLevelService.getShopUserLevelById(id);
        return ResponseObject.ok().put("userLevel", userLevel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("userlevel:save")
    public ResponseObject save(@RequestBody UserLevel userLevel) {
        shopUserLevelService.saveShopUserLevel(userLevel);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("userlevel:update")
    public ResponseObject update(@RequestBody UserLevel userLevel) {
        shopUserLevelService.updateShopUserLevelById(userLevel);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("userlevel:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        shopUserLevelService.deleteShopUserLevelBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @RequestMapping("queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<UserLevel> userLevelList = shopUserLevelService.queryShopUserLevelList(params);
        return ResponseObject.ok().put("list", userLevelList);
    }
}
