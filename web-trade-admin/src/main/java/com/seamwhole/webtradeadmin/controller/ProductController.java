package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.Product;
import com.seamwhole.webtradeadmin.info.ProductDO;
import com.seamwhole.webtradeadmin.service.ProductService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<ProductDO> page=productService.queryByPage(params);
        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        Product product = productService.queryObject(id);
        return ResponseObject.ok().put("product", product);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:save")
    public ResponseObject save(@RequestBody Product product) {
        productService.save(product);
        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:update")
    public ResponseObject update(@RequestBody Product product) {
        productService.update(product);
        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        productService.deleteBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<ProductDO> list = productService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 根据goodsId查询商品
     *
     * @param goodsId
     * @return
     */
    @RequestMapping("/queryByGoodsId/{goodsId}")
    public ResponseObject queryByGoodsId(@PathVariable("goodsId") String goodsId) {
        Map<String, Object> params = new HashMap<>();
        params.put("goodsId", goodsId);
        List<ProductDO> list = productService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }
}
