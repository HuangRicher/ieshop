package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopGoodsDO;
import com.seamwhole.servicetradecore.model.Goods;
import com.seamwhole.servicetradecore.service.GoodsService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsResource {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/queryByPage")
    public PagesInfo<ShopGoodsDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopGoodsDO> pageInfo=goodsService.queryShopByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopGoodsDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public Goods queryObject(@PathVariable("id") Integer id){
        return goodsService.queryObject(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Goods goods){
        goodsService.saveShopGoods(goods, , );
    }

    @PostMapping("/update")
    public void update(@RequestBody Goods goods){
        goodsService.update(goods);
    }

    @PostMapping("/deleteBatch/{userId}")
    public void deleteBatch(@RequestBody Integer[] ids,@PathVariable("userId") Long userId){
        goodsService.deleteShopBatch(ids, userId);
    }

    @PostMapping("/queryList")
    public List<ShopGoodsDO> queryList(@RequestBody Map<String, Object> params){
        return goodsService.queryShopList(params);
    }

    @PostMapping("/back/{userId}")
    public void back(@RequestBody Integer[] ids,@PathVariable("userId") Long userId){
        goodsService.back(ids, userId);
    }

    @PostMapping("/queryTotal")
    public int queryTotal(@RequestBody Map<String, Object> params){
        return goodsService.queryShopToTal(params);
    }

    @PostMapping("/enSale/{userId}/{id}")
    public void enSale(@PathVariable("id") Integer id,@PathVariable("userId") Long userId){
        goodsService.enSale(id, userId);
    }

    @PostMapping("/unSale/{userId}/{id}")
    public void unSale(@PathVariable("id") Integer id,@PathVariable("userId") Long userId){
        goodsService.unSale(id, userId);
    }

}
