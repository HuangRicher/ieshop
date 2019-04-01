package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopAd;
import com.seamwhole.webtradeadmin.info.ShopAdDO;
import com.seamwhole.webtradeadmin.service.ShopAdService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private ShopAdService shopAdService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ad:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<ShopAdDO> page=shopAdService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ad:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        ShopAd ad = shopAdService.queryObject(id);
        return ResponseObject.ok().put("ad", ad);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ad:save")
    public ResponseObject save(@RequestBody ShopAd ad) {
        shopAdService.save(ad);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ad:update")
    public ResponseObject update(@RequestBody ShopAd ad) {
        shopAdService.update(ad);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ad:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        shopAdService.deleteBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<ShopAdDO> list = shopAdService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }
}
