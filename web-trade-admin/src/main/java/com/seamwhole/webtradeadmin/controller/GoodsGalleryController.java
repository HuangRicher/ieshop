package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("goodsgallery")
public class GoodsGalleryController {
    @Autowired
    private GoodsGalleryService goodsGalleryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsgallery:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<GoodsGalleryEntity> goodsGalleryList = goodsGalleryService.queryList(query);
        int total = goodsGalleryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(goodsGalleryList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsgallery:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        GoodsGalleryEntity goodsGallery = goodsGalleryService.queryObject(id);

        return ResponseObject.ok().put("goodsGallery", goodsGallery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsgallery:save")
    public ResponseObject save(@RequestBody GoodsGalleryEntity goodsGallery) {
        goodsGalleryService.save(goodsGallery);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsgallery:update")
    public ResponseObject update(@RequestBody GoodsGalleryEntity goodsGallery) {
        goodsGalleryService.update(goodsGallery);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goodsgallery:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        goodsGalleryService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<GoodsGalleryEntity> list = goodsGalleryService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
