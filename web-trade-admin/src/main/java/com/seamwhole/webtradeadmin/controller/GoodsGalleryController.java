package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.GoodsGallery;
import com.seamwhole.webtradeadmin.info.GoodsGalleryDO;
import com.seamwhole.webtradeadmin.service.GoodsGalleryService;
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
        PagesInfo<GoodsGalleryDO> page=goodsGalleryService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsgallery:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        GoodsGallery goodsGallery = goodsGalleryService.queryObject(id);

        return ResponseObject.ok().put("goodsGallery", goodsGallery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsgallery:save")
    public ResponseObject save(@RequestBody GoodsGallery goodsGallery) {
        goodsGalleryService.save(goodsGallery);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsgallery:update")
    public ResponseObject update(@RequestBody GoodsGallery goodsGallery) {
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

        List<GoodsGalleryDO> list = goodsGalleryService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
