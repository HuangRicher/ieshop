package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ProductDO;
import com.seamwhole.servicetradecore.model.Product;
import com.seamwhole.servicetradecore.service.ProductService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping("/queryList")
    public List<ProductDO> queryList(@RequestBody Map<String, Object> params){
        return productService.queryShopProductList(params);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        productService.deleteBatch(ids);
    }

    @PostMapping("/update")
    public void update(@RequestBody Product product){
        productService.updateById(product);
    }

    @PostMapping("/save")
    public void save(@RequestBody Product product){
        productService.saveShop(product);
    }

    @PostMapping("/queryObject/{id}")
    public Product queryObject(@PathVariable("id") Integer id){
        return productService.getById(id);
    }

    @PostMapping("/queryByPage")
    public PagesInfo<ProductDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ProductDO> page=productService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ProductDO>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }
}
