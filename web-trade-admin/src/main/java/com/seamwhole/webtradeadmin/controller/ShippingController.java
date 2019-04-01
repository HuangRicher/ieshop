package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopShipping;
import com.seamwhole.webtradeadmin.service.ShippingService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("shipping")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shipping:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<ShopShipping> page=shippingService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shipping:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        ShopShipping shipping = shippingService.queryObject(id);
        return ResponseObject.ok().put("shipping", shipping);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shipping:save")
    public ResponseObject save(@RequestBody ShopShipping shipping) {
        shippingService.save(shipping);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shipping:update")
    public ResponseObject update(@RequestBody ShopShipping shipping) {
        shippingService.update(shipping);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shipping:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        shippingService.deleteBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<ShopShipping> list = shippingService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }
}
