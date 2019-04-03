package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.GoodsGalleryDO;
import com.seamwhole.servicetradecore.model.GoodsGallery;
import com.seamwhole.servicetradecore.service.GoodsGalleryService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsGallery")
public class GoodsGalleryResource {

    @Autowired
    private GoodsGalleryService goodsGalleryService;


    @PostMapping("/queryByPage")
    public PagesInfo<GoodsGalleryDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<GoodsGalleryDO> pageInfo=goodsGalleryService.queryShopByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<GoodsGalleryDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @PostMapping("/queryObject/{id}")
    public GoodsGallery queryObject(@PathVariable("id") Integer id){
        return goodsGalleryService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody GoodsGallery goodsGallery){
        goodsGalleryService.save(goodsGallery);
    }

    @PostMapping("/update")
    public void update(@RequestBody GoodsGallery goodsGallery){
        goodsGalleryService.updateById(goodsGallery);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        goodsGalleryService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<GoodsGalleryDO> queryList(@RequestBody Map<String, Object> params){
        return goodsGalleryService.queryShopList(params);
    }
}
