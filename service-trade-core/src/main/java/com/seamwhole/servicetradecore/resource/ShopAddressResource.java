package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.AddressDO;
import com.seamwhole.servicetradecore.model.ShopAddress;
import com.seamwhole.servicetradecore.service.AddressService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shopAddress")
public class ShopAddressResource {

    @Autowired
    private AddressService addressService;

    @PostMapping("/queryShopUserAddressByPage")
    public PagesInfo<AddressDO> queryShopUserAddressByPage(@RequestBody Map<String, Object> params){
        PageInfo<AddressDO> page=addressService.queryByPage(params,Integer.valueOf((String)params.get("page")),
                Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<AddressDO>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }

    @GetMapping("/queryObject/{id}")
    public AddressDO queryObject(@PathVariable("id") Integer id){
        return addressService.queryObject(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ShopAddress address){
        addressService.save(address);
    }

    @PostMapping("/update")
    public void update(@RequestBody ShopAddress address){
        addressService.updateById(address);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        addressService.deleteBatch(ids);
    }
}
