package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Goods;
import com.seamwhole.webtradeadmin.info.ShopGoodsDO;
import com.seamwhole.webtradeadmin.service.GoodsService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<ShopGoodsDO> page=goodsService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        Goods goods = goodsService.queryObject(id);

        return ResponseObject.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goods:save")
    public ResponseObject save(@RequestBody Goods goods) {
        goodsService.save(goods);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goods:update")
    public ResponseObject update(@RequestBody Goods goods) {
        goodsService.update(goods);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goods:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        goodsService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        params.put("isDelete", 0);
        List<ShopGoodsDO> list = goodsService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }


    /**
     * 商品回收站
     *
     * @param params
     * @return
     */
    @RequestMapping("/historyList")
    public ResponseObject historyList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        params.put("isDelete", 1);
        PagesInfo<ShopGoodsDO> page=goodsService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 商品从回收站恢复
     */
    @RequestMapping("/back")
    @RequiresPermissions("goods:back")
    public ResponseObject back(@RequestBody Integer[] ids) {
        goodsService.back(ids);

        return ResponseObject.ok();
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public ResponseObject queryTotal(@RequestParam Map<String, Object> params) {

        params.put("isDelete", 0);
        int sum = goodsService.queryTotal(params);
        return ResponseObject.ok().put("goodsSum", sum);
    }

    /**
     * 上架
     */
    @RequestMapping("/enSale")
    public ResponseObject enSale(@RequestBody Integer id) {
        goodsService.enSale(id);

        return ResponseObject.ok();
    }

    /**
     * 下架
     */
    @RequestMapping("/unSale")
    public ResponseObject unSale(@RequestBody Integer id) {
        goodsService.unSale(id);

        return ResponseObject.ok();
    }
}
