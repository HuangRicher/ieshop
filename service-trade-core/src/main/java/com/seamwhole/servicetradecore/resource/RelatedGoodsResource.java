package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.RelatedGoods;
import com.seamwhole.servicetradecore.service.RelatedGoodsService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/relatedGoods")
public class RelatedGoodsResource {

    @Autowired
    private RelatedGoodsService relatedGoodsService;


    @PostMapping("/queryByPage")
    public PagesInfo<RelatedGoods> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<RelatedGoods> pageInfo=relatedGoodsService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<RelatedGoods>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public RelatedGoods queryObject(@PathVariable("id") Integer id){
        return relatedGoodsService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody RelatedGoods relatedGoods){
        relatedGoodsService.save(relatedGoods);
    }

    @PostMapping("/update")
    public void update(@RequestBody RelatedGoods relatedGoods){
        relatedGoodsService.updateById(relatedGoods);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        relatedGoodsService.deleteBatch(ids);
    }
}
