package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.ShopBrand;
import com.seamwhole.servicetradecore.service.BrandService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandResource {

    @Autowired
    private BrandService brandService;


    @PostMapping("/queryByPage")
    public PagesInfo<ShopBrand> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopBrand> pageInfo=brandService.queryByPage(Integer.valueOf((String)params.get("limit")),Integer.valueOf((String)params.get("page")),params,"");
        return new PagesInfo<ShopBrand>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public ShopBrand queryObject(@PathVariable("id") Integer id){
        return brandService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ShopBrand brand){
        brandService.save(brand);
    }

    @PostMapping("/update")
    public void update(@RequestBody ShopBrand brand){
        brandService.update(brand);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        brandService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<ShopBrand> queryList(@RequestBody Map<String, Object> params){
        return brandService.queryList(params);
    }

}
