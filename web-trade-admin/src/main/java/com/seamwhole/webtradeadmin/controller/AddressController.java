package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.AddressDO;
import com.seamwhole.webtradeadmin.info.ShopAddress;
import com.seamwhole.webtradeadmin.service.AddressService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("address:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<AddressDO> page=addressService.queryShopUserAddressByPage(params);
        return ResponseObject.ok().put("page", page);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("address:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        AddressDO address = addressService.queryObject(id);
        return ResponseObject.ok().put("address", address);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("address:save")
    public ResponseObject save(@RequestBody ShopAddress address) {
        addressService.save(address);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("address:update")
    public ResponseObject update(@RequestBody ShopAddress address) {
        addressService.update(address);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("address:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        addressService.deleteBatch(ids);
        return ResponseObject.ok();
    }
}
