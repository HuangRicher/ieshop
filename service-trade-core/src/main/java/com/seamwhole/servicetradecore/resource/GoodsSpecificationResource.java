package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopGoodsSpecificationDO;
import com.seamwhole.servicetradecore.model.GoodsSpecification;
import com.seamwhole.servicetradecore.service.GoodsSpecificationService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsSpecification")
public class GoodsSpecificationResource {

    @Autowired
    private GoodsSpecificationService goodsSpecificationService;


    @PostMapping("/queryList")
    public List<ShopGoodsSpecificationDO> queryList(@RequestBody Map<String, Object> params){
        return goodsSpecificationService.queryShopList(params);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        goodsSpecificationService.deleteBatch(ids);
    }

    @PostMapping("/update")
    public void update(@RequestBody GoodsSpecification goodsSpecification){
        goodsSpecificationService.update(goodsSpecification);
    }

    @PostMapping("/save")
    public void save(@RequestBody GoodsSpecification goodsSpecification){
        goodsSpecificationService.save(goodsSpecification);
    }

    @GetMapping("/queryObject/{id}")
    public GoodsSpecification queryObject(@PathVariable("id") Integer id){
        return goodsSpecificationService.queryObject(id);
    }

    @PostMapping("/queryByPage")
    public PagesInfo<ShopGoodsSpecificationDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopGoodsSpecificationDO> pageInfo=goodsSpecificationService.queryShopByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopGoodsSpecificationDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }
}
