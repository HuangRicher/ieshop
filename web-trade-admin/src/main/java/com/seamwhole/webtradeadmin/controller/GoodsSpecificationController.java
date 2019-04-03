package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.GoodsSpecification;
import com.seamwhole.webtradeadmin.info.ShopGoodsSpecificationDO;
import com.seamwhole.webtradeadmin.service.GoodsSpecificationService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品对应规格表值表Controller
 */
@RestController
@RequestMapping("/goodsspecification")
public class GoodsSpecificationController {
    @Autowired
    private GoodsSpecificationService goodsSpecificationService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsspecification:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<ShopGoodsSpecificationDO> page=goodsSpecificationService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsspecification:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        GoodsSpecification goodsSpecification = goodsSpecificationService.queryObject(id);

        return ResponseObject.ok().put("goodsSpecification", goodsSpecification);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsspecification:save")
    public ResponseObject save(@RequestBody GoodsSpecification goodsSpecification) {
        goodsSpecificationService.save(goodsSpecification);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsspecification:update")
    public ResponseObject update(@RequestBody GoodsSpecification goodsSpecification) {
        goodsSpecificationService.update(goodsSpecification);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goodsspecification:delete")
    public ResponseObject delete(@RequestBody Integer[]ids) {
        goodsSpecificationService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<ShopGoodsSpecificationDO> list = goodsSpecificationService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
